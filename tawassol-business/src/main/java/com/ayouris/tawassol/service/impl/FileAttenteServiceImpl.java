package com.ayouris.tawassol.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.dsl.BooleanExpression;

import com.ayouris.tawassol.admin.model.enums.ViewName;
import com.ayouris.tawassol.common.exception.ErrorMessageType;
import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.ControleCamionConformityBean;
import com.ayouris.tawassol.common.model.bean.FileAttenteBean;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.LigneFileAttenteBean;
import com.ayouris.tawassol.common.model.bean.OrdreLivraisonBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.entity.Client;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.ControleCamionConformity;
import com.ayouris.tawassol.common.model.entity.FileAttente;
import com.ayouris.tawassol.common.model.entity.LigneFileAttente;
import com.ayouris.tawassol.common.model.entity.OrdreLivraison;
import com.ayouris.tawassol.common.model.entity.QFileAttente;
import com.ayouris.tawassol.common.model.entity.QOrdreLivraison;
import com.ayouris.tawassol.common.model.entity.Site;
import com.ayouris.tawassol.common.model.enums.CategorieTypeFileAttente;
import com.ayouris.tawassol.common.model.enums.StatutFileAttente;
import com.ayouris.tawassol.common.model.enums.StatutOrdreLivraison;
import com.ayouris.tawassol.common.model.enums.TypeChargement;
import com.ayouris.tawassol.repository.FileAttenteRepository;
import com.ayouris.tawassol.repository.OrdreLivraisonRepository;
import com.ayouris.tawassol.service.BonLivraisonService;
import com.ayouris.tawassol.service.ColumnDefService;
import com.ayouris.tawassol.service.ControleCamionService;
import com.ayouris.tawassol.service.FileAttenteService;
import com.ayouris.tawassol.service.JournalisationService;
import com.ayouris.tawassol.service.LigneFileAttenteService;
import com.ayouris.tawassol.service.OrdreLivraisonService;
import com.ayouris.tawassol.service.ServiceException;

@Service
public class FileAttenteServiceImpl extends GenericServiceImpl<FileAttente, Long> implements FileAttenteService {

	@Autowired
	private FileAttenteRepository fileAttenteRepository;

	@Autowired
	private OrdreLivraisonRepository ordreLivraisonRepository;

	@Autowired
	private ColumnDefService columnDefService;

	@Autowired
	private OrdreLivraisonService ordreLivraisonService;

	@Autowired
	private BonLivraisonService bonLivraisonService;

	@Autowired
	private ControleCamionService controleCamionService;

	@Autowired
	private LigneFileAttenteService ligneFileAttenteService;

	@Autowired
	private CustomModelMapper mapper;

	@Autowired
	private JournalisationService journalisationService;

	@Autowired
	private RapprochementServiceImpl rapprochementService;

	private static final Logger LOGGER = LoggerFactory.getLogger(FileAttenteServiceImpl.class);

	@Override
	public String create(FileAttenteBean fileAttenteBean) {
		validateRequiredValues(fileAttenteBean);
		validateNumBcUnicity(fileAttenteBean);

		if (StringUtils.isBlank(fileAttenteBean.getNumBC())) {
			fileAttenteBean.setStatut(StatutFileAttente.PROVISOIRE);
		} else {
			fileAttenteBean.setStatut(StatutFileAttente.AU_PARKING);
		}
		
		FileAttente entity = (FileAttente) mapper.mapStrict(fileAttenteBean, FileAttente.class);
		for (LigneFileAttente ligne : entity.getLigneFileAttente()) {
			ligne.setFileAttente(entity);
		}
		
		try {
			if (!isNew(entity)) {
				FileAttente oldEntity = findOne(entity.getId());
				entity.setNumOL(oldEntity.getNumOL());
				if (oldEntity.getStatut().equals(StatutFileAttente.A_RECTIFIER)
						&& CategorieTypeFileAttente.LIVRAISON_CONDITIONNE
						.equals(fileAttenteBean.getTypeFileAttente().getCategorie())) {
					if (StringUtils.isBlank(fileAttenteBean.getMotif())) {
						throw new ServiceException(ErrorMessageType.FILE_ATTENTE_MISSING_MOTIF);
					}
					journalisationService.logFileAttente(oldEntity, entity, fileAttenteBean.getMotif());
					entity.setStatut(StatutFileAttente.A_RECTIFIER);

					boolean lien = false;
					List<String> infoRapproch = new ArrayList<String>();

					OrdreLivraisonBean ordreLivraisonBean = getOLRectifier(oldEntity);
					if (oldEntity.getNumOL().equalsIgnoreCase(ordreLivraisonBean.getNumOL())
							&& entity.getNumBC().equalsIgnoreCase(ordreLivraisonBean.getNumBC())
							&& entity.getClient().getId().equals(ordreLivraisonBean.getClient().getId())
							&& entity.getConcessionnaire().getId()
							.equals(ordreLivraisonBean.getConcessionnaire().getId())) {
						lien = true;
					}
					if (!lien) {
						throw new ServiceException(ErrorMessageType.FILE_ATTENTE_ORDRE_LIVRAISON_LINK_INVALID);
					}

					infoRapproch = rapprochementService.doRapprochement(ordreLivraisonBean, fileAttenteBean);
					if (!infoRapproch.isEmpty()) {

						entity.setStatut(StatutFileAttente.A_RECTIFIER);
						ordreLivraisonBean.setStatut(StatutOrdreLivraison.A_RECTIFIER);
						try {

							save(entity);
							ordreLivraisonService.updatefromFileAttente(ordreLivraisonBean);
							StringBuilder warning = new StringBuilder();
							for (String result : infoRapproch) {
								warning.append("\n - ").append(result);
							}
							return String.valueOf(entity.getId()) + ";Informations incohérents :"
									+ warning.toString();
						} catch (DataIntegrityViolationException ex) {
							LOGGER.error(ex.getMessage());
							throw ex;
						}
					} else {
						entity.setStatut(StatutFileAttente.AU_CENTRE);
						ordreLivraisonBean.setStatut(StatutOrdreLivraison.EN_INSTANCE);
					}

					try {
						ordreLivraisonService.updatefromFileAttente(ordreLivraisonBean);
						OrdreLivraison entityOrdre = (OrdreLivraison) mapper.mapStrict(ordreLivraisonBean,
								OrdreLivraison.class);
						if (!ordreLivraisonService.existeBonLivraison(entityOrdre)) {
							bonLivraisonService.createFromOL(entityOrdre).getOrdreLivraison();

						}
						save(entity);
						return String.valueOf(entity.getId());

					} catch (DataIntegrityViolationException ex) {
						LOGGER.error(ex.getMessage());
						throw ex;
					}
				}

			}

			return String.valueOf(save(entity).getId());
		} catch (

				DataIntegrityViolationException ex) {
			LOGGER.error(ex.getMessage());
			throw ex;
		}
	}

	@Override
	public void updatefromOL(FileAttenteBean fileAttenteBean) {

		FileAttente entity = (FileAttente) mapper.mapStrict(fileAttenteBean, FileAttente.class);

		try {
			save(entity);
		} catch (DataIntegrityViolationException ex) {
			LOGGER.error(ex.getMessage());
			throw ex;
		}
	}

	private void validateNumBcUnicity(FileAttenteBean fileAttenteBean) {
		if (fileAttenteBean.getNumBC() != null) {
			QFileAttente fileAttente = QFileAttente.fileAttente;
			Site site = mapper.map(fileAttenteBean.getSite(), Site.class);
			Client client = mapper.map(fileAttenteBean.getClient(), Client.class);
			BooleanExpression predicat = fileAttente.numBC.eq(fileAttenteBean.getNumBC())
					.and(fileAttente.site.eq(site))
					.and(fileAttente.client.eq(client))
					.and(fileAttente.statut.ne(StatutFileAttente.ANNULE));

			if (fileAttenteBean.getId() != null) {
				predicat = predicat.and(fileAttente.id.ne(fileAttenteBean.getId()));
			}

			FileAttente existedEntity = fileAttenteRepository.findOne(predicat);

			if (existedEntity != null) {
				throw new ServiceException(ErrorMessageType.FILE_ATTENTE_NUMBC_ALREADY_EXISTS);
			}
		}
	}


	private void validateRequiredValues(FileAttenteBean fileAttenteBean) {
		if (fileAttenteBean.getHeureArrivee() == null || fileAttenteBean.getTypeFileAttente() == null
				|| fileAttenteBean.getStatut() == null || fileAttenteBean.getConcessionnaire() == null
				|| fileAttenteBean.getClient() == null || fileAttenteBean.getChauffeur() == null
				|| fileAttenteBean.getCamion() == null || fileAttenteBean.getSite() == null) {

			throw new ServiceException(ErrorMessageType.FILE_ATTENTE_MISSING_REQUIRED_VALUES);
		}
		if (fileAttenteBean.getTypeFileAttente().getCategorie() == CategorieTypeFileAttente.LIVRAISON_VRAC) {
			if (fileAttenteBean.getCiterne() == null) {
				throw new ServiceException(ErrorMessageType.FILE_ATTENTE_MISSING_REQUIRED_VALUES);
			}
		}

		List<LigneFileAttenteBean> ligneFileAttentes = fileAttenteBean.getLigneFileAttente();
		if (ligneFileAttentes.size() <= 0) {
			throw new ServiceException(ErrorMessageType.FILE_ATTENTE_MISSING_LIGNE_FILE_ATTENTE);
		}
		for (LigneFileAttenteBean ligneFileAttente : ligneFileAttentes) {
			ligneFileAttenteService.validateLigneFileAttenteBean(ligneFileAttente);
		}
	}

	@Override
	public GridListBean<FileAttenteBean> list(PageDataBean paginateData) {
		return fileAttenteRepository.findEntities(paginateData, getColumnDef());
	}

	@Override
	@Transactional
	public FileAttenteBean getDetails(Long id) {
		FileAttente fileAttente = findOne(id);
		return mapper.map(fileAttente, FileAttenteBean.class);
	}

	public Map<String, ColumnDef> getColumnDef() {
		return columnDefService.getColumnDefMapByViewName(ViewName.FILE_ATTENTE);
	}

	@Override
	@Transactional
	public List<FileAttenteBean> getAll() {
		List<FileAttente> fileAttentes = findAll();
		return mapper.map(fileAttentes, FileAttenteBean.LIST_BEAN_TYPE);
	}


	@Override
	@Transactional
	public List<FileAttenteBean> getFileAttenteEntree() {
		QFileAttente fileAttente = QFileAttente.fileAttente;
		Iterable<FileAttente> fileAttenteIterable = fileAttenteRepository
				.findAll(fileAttente.statut.eq(StatutFileAttente.A_ENTREE));
		List<FileAttente> fileAttentes = new ArrayList<FileAttente>();
		fileAttenteIterable.forEach(fileAttentes::add);
		return mapper.map(fileAttentes, FileAttenteBean.LIST_BEAN_TYPE);
	}

	@Override
	@Transactional
	public List<FileAttenteBean> getFileAttenteRectifier() {
		QFileAttente fileAttente = QFileAttente.fileAttente;
		Iterable<FileAttente> fileAttenteIterable = fileAttenteRepository
				.findAll(fileAttente.statut.eq(StatutFileAttente.A_RECTIFIER));
		List<FileAttente> fileAttentes = new ArrayList<FileAttente>();
		fileAttenteIterable.forEach(fileAttentes::add);
		return mapper.map(fileAttentes, FileAttenteBean.LIST_BEAN_TYPE);
	}

	@Override
	@Transactional
	public OrdreLivraisonBean getOLRectifier(FileAttente fileAttente) {
		QOrdreLivraison ordre = QOrdreLivraison.ordreLivraison;
		OrdreLivraison ordreLivraison = ordreLivraisonRepository.findOne(ordre.numOL.eq(fileAttente.getNumOL()));
		if(ordreLivraison == null) {
			throw new ServiceException(ErrorMessageType.FILE_ATTENTE_ORDRE_LIVRAISON_LINK_INVALID);
		}
		ordreLivraison.getConcessionnaire().setConditionCommerciale(null); // workaround
		return mapper.map(ordreLivraison, OrdreLivraisonBean.class);
	}

	@Override
	@Transactional
	public FileAttenteBean getFileAttenteLinkOL(OrdreLivraisonBean ordre) {
		QFileAttente qfile = QFileAttente.fileAttente;
		FileAttente file = fileAttenteRepository.findOne(qfile.numOL.eq(ordre.getNumOL()));
		return mapper.map(file, FileAttenteBean.class);
	}

	@Override
	@Transactional
	public void setControleCamionConformity(FileAttenteBean fileAttenteBean) {
		if (fileAttenteBean.getId() != null) {
			FileAttente fileAttente = findOne(fileAttenteBean.getId());
			FileAttente entity = (FileAttente) mapper.mapStrict(fileAttenteBean, FileAttente.class);
			List<ControleCamionConformity> ccc = entity.getControleCamionConformities();
			List<ControleCamionConformity> controleCamionConformities = fileAttente.getControleCamionConformities();
			if (ccc != null) {
				for (ControleCamionConformity value : ccc) {
					value.setFileAttente(entity);
				}
				if (controleCamionConformities == null) {
					controleCamionConformities = ccc;
				} else {
					controleCamionConformities.retainAll(ccc);
					controleCamionConformities.addAll(ccc);
				}
			}
			fileAttente.setStatut(StatutFileAttente.A_ENTREE);
			save(fileAttente);
		}

	}

	private TypeChargement getTypeChargement(FileAttente fileAttente) {
		if (CategorieTypeFileAttente.LIVRAISON_CONDITIONNE.equals(fileAttente.getTypeFileAttente().getCategorie())) {
			return TypeChargement.CONDITIONNE;
		} else {
			return TypeChargement.VRAC;
		}
	}

	@Override
	public List<ControleCamionConformityBean> getControleCamionConformities(Long id) {
		FileAttente fileAttente = findOne(id);
		return controleCamionService.getControleCamionConformitiesBeans(getTypeChargement(fileAttente));
	}


	@Override
	public void annulerByOrdreLivraison(OrdreLivraison ordreLivraison) {

		FileAttente fileAttente = getFileAttenteByNumOl(ordreLivraison.getNumOL());
		if (fileAttente != null) {
			fileAttente.setStatut(StatutFileAttente.ANNULE);
			save(fileAttente);
		}
	}

	@Override
	public void setAlaSortieByOrdreLivraison(OrdreLivraison ordreLivraison) {
		FileAttente fileAttente = getFileAttenteByNumOl(ordreLivraison.getNumOL());
		if (fileAttente != null) {
			fileAttente.setStatut(StatutFileAttente.A_LA_SORTIE);
			save(fileAttente);
		}
	}

	@Override
	public void setAuCentreOrdreLivraison(OrdreLivraison ordreLivraison) {
		FileAttente fileAttente = getFileAttenteByNumOl(ordreLivraison.getNumOL());
		if (fileAttente != null) {
			fileAttente.setStatut(StatutFileAttente.AU_CENTRE);
			save(fileAttente);
		}
	}

	private FileAttente getFileAttenteByNumOl(String numOl) {
		QFileAttente qfile = QFileAttente.fileAttente;
		return fileAttenteRepository.findOne(qfile.numOL.eq(numOl));
	}


}