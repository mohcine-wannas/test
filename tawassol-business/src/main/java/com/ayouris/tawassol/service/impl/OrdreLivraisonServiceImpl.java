package com.ayouris.tawassol.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.ayouris.tawassol.common.model.bean.FileAttenteBean;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.JourneeActiviteBean;
import com.ayouris.tawassol.common.model.bean.LigneOrdreLivraisonBean;
import com.ayouris.tawassol.common.model.bean.LigneOrdreLivraisonVracBean;
import com.ayouris.tawassol.common.model.bean.OrdreLivraisonBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.entity.BonLivraison;
import com.ayouris.tawassol.common.model.entity.Client;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.JourneeActivite;
import com.ayouris.tawassol.common.model.entity.LigneOrdreLivraison;
import com.ayouris.tawassol.common.model.entity.OrdreLivraison;
import com.ayouris.tawassol.common.model.entity.QBonLivraison;
import com.ayouris.tawassol.common.model.entity.QOrdreLivraison;
import com.ayouris.tawassol.common.model.entity.Site;
import com.ayouris.tawassol.common.model.enums.ActiviteJourneeActivite;
import com.ayouris.tawassol.common.model.enums.StatutFileAttente;
import com.ayouris.tawassol.common.model.enums.StatutOrdreLivraison;
import com.ayouris.tawassol.common.model.enums.TypeBon;
import com.ayouris.tawassol.common.model.enums.TypeChargement;
import com.ayouris.tawassol.repository.BonLivraisonRepository;
import com.ayouris.tawassol.repository.OrdreLivraisonRepository;
import com.ayouris.tawassol.service.BonLivraisonService;
import com.ayouris.tawassol.service.ColumnDefService;
import com.ayouris.tawassol.service.FileAttenteService;
import com.ayouris.tawassol.service.LigneOLVRACService;
import com.ayouris.tawassol.service.LigneOrdreLivraisonService;
import com.ayouris.tawassol.service.OrdreLivraisonService;
import com.ayouris.tawassol.service.ServiceException;
import com.ayouris.tawassol.service.SiteService;

@Service
public class OrdreLivraisonServiceImpl extends GenericServiceImpl<OrdreLivraison, Long>
		implements OrdreLivraisonService {

	@Autowired
	private OrdreLivraisonRepository ordreLivraisonRepository;

	@Autowired
	private BonLivraisonRepository bonLivraisonRepository;

	@Autowired
	private ColumnDefService columnDefService;

	@Autowired
	private LigneOrdreLivraisonService ligneOrdreLivraisonService;

	@Autowired
	private LigneOLVRACService ligneOLVRACService;

	@Autowired
	private BonLivraisonService bonLivraisonService;

	@Autowired
	private FileAttenteService fileAttenteService;

	@Autowired
	private RapprochementServiceImpl rapprochementService;

	@Autowired
	private JournalisationServiceImpl journalisationService;

	@Autowired
	private SiteService siteService;

	@Autowired
	private CustomModelMapper mapper;

	@Autowired
	private CodificationServiceImpl codificationServiceImpl = new CodificationServiceImpl();

	private static final Logger LOGGER = LoggerFactory.getLogger(OrdreLivraisonServiceImpl.class);

	@Override
	@Transactional
	public void annuler(Long id, String motif) {

		OrdreLivraison entity = findOne(id);
		if (entity == null) {
			throw new ServiceException(ErrorMessageType.ORDRE_LIVRAISON_NOT_FOUND);
		}
		if (!StatutOrdreLivraison.EN_INSTANCE.equals(entity.getStatut())) {
			throw new ServiceException(ErrorMessageType.ORDRE_LIVRAISON_EN_INSTANCE_ANNULER);
		}

		entity.setStatut(StatutOrdreLivraison.ANNULE);
		journalisationService.logAnnulation(entity, motif);
		bonLivraisonService.annulerByOrdreLivraison(entity, motif);
		save(entity);
	}

	public String create(OrdreLivraisonBean ordreLivraisonBean) {

		OrdreLivraison entity = new OrdreLivraison();
		OrdreLivraison oldEntity = null;
		FileAttenteBean fileAttenteBean = null;

		validateRequiredValues(ordreLivraisonBean);
		entity = (OrdreLivraison) mapper.mapStrict(ordreLivraisonBean, OrdreLivraison.class);
		validateNumBcUnicity(ordreLivraisonBean);
		for (LigneOrdreLivraison ligne : entity.getLigneOrdreLivraison()) {
			ligne.setOrdreLivraison(entity);
		}
		validateJourneeActiviteLivraison(entity);

		if (TypeChargement.CONDITIONNE.equals(ordreLivraisonBean.getTypeChargement())) {

			validateRequiredValuesLigneOLCond(ordreLivraisonBean);

			boolean lien = false;
			List<String> infoRapproch = new ArrayList<String>();
			List<FileAttenteBean> fileAttentes = new ArrayList<FileAttenteBean>();

			if (!isNew(entity)) { // en cas de rectification, refaire la
									// premiere partie du rapprochement
				fileAttenteBean = fileAttenteService.getFileAttenteLinkOL(ordreLivraisonBean);
				if (fileAttenteBean.getNumOL().equalsIgnoreCase(ordreLivraisonBean.getNumOL())
						&& fileAttenteBean.getNumBC().equalsIgnoreCase(ordreLivraisonBean.getNumBC())
						&& fileAttenteBean.getClient().getId().equals(ordreLivraisonBean.getClient().getId())
						&& fileAttenteBean.getConcessionnaire().getId()
								.equals(ordreLivraisonBean.getConcessionnaire().getId())) {
					lien = true;
				}
			} else {// nouvel enregistrement
				fileAttentes = fileAttenteService.getFileAttenteEntree();
				for (FileAttenteBean item : fileAttentes) {
					if (item.getNumBC() != null && item.getNumBC().equalsIgnoreCase(ordreLivraisonBean.getNumBC())
							&& item.getClient().getId().equals(ordreLivraisonBean.getClient().getId())
							&& item.getConcessionnaire().getId()
									.equals(ordreLivraisonBean.getConcessionnaire().getId())) {
						lien = true;
						fileAttenteBean = item;
					}
				}
			}

			if (!lien) { // si la premiere partie du rapprochement est non
							// valide
				throw new ServiceException(ErrorMessageType.ORDRE_LIVRAISON_FILE_ATTENTE_LINK_INVALID);
			}

			infoRapproch = rapprochementService.doRapprochement(ordreLivraisonBean, fileAttenteBean); // 2
																										// ieme
																										// partie
																										// du
																										// rapprochement

			if (isNew(entity)) {
				entity.setNumOL(codificationServiceImpl.getGeneratedCode(OrdreLivraison.class));
				fileAttenteBean.setNumOL(entity.getNumOL());
			} else { // Journalisation en cas de réctification
				oldEntity = findOne(entity.getId());
				if (oldEntity.getStatut().equals(StatutOrdreLivraison.A_RECTIFIER)
						&& TypeChargement.CONDITIONNE.equals(oldEntity.getTypeChargement())) {
					if (StringUtils.isBlank(ordreLivraisonBean.getMotif())) {
						throw new ServiceException(ErrorMessageType.FILE_ATTENTE_MISSING_MOTIF);
					}
					journalisationService.logOrdreLivraison(oldEntity, entity, ordreLivraisonBean.getMotif());
				}
			}

			if (!infoRapproch.isEmpty()) { // 2ieme partie n'est pas valide==>
											// Enregistrement des instances avec
											// statut rectif
				fileAttenteBean.setStatut(StatutFileAttente.A_RECTIFIER);
				entity.setStatut(StatutOrdreLivraison.A_RECTIFIER);
				try {

					save(entity);
					fileAttenteService.updatefromOL(fileAttenteBean);
					StringBuilder warning = new StringBuilder();
					for (String result : infoRapproch) {
						warning.append("\n - ").append(result);
					}
					return String.valueOf(entity.getId()) + ";Informations incohérents :" + warning.toString();
				} catch (DataIntegrityViolationException ex) {
					LOGGER.error(ex.getMessage());
					throw ex;
				}
			} else {
				if (!isNew(entity)) {
					oldEntity = findOne(entity.getId());
					if (StatutOrdreLivraison.A_RECTIFIER.equals(oldEntity.getStatut())) {
						fileAttenteBean.setStatut(StatutFileAttente.AU_CENTRE);
						entity.setStatut(StatutOrdreLivraison.EN_INSTANCE);
					}

					try {
						fileAttenteService.updatefromOL(fileAttenteBean);
						if (!existeBonLivraison(entity)) {
							bonLivraisonService.createFromOL(entity).getOrdreLivraison();
							save(entity);
							return String.valueOf(entity.getId());
						} else {
							return String.valueOf(save(entity).getId());
						}

					} catch (DataIntegrityViolationException ex) {
						LOGGER.error(ex.getMessage());
						throw ex;
					}
				} else {
					try {
						fileAttenteService.updatefromOL(fileAttenteBean);
						save(entity);
						bonLivraisonService.createFromOL(entity).getOrdreLivraison().getId();
						return String.valueOf(entity.getId());

					} catch (DataIntegrityViolationException ex) {
						LOGGER.error(ex.getMessage());
						throw ex;
					}

				}

			}

		} else {

			ordreLivraisonBean.setTypeBon(TypeBon.BON_COMMANDE);
			LigneOrdreLivraisonVracBean ligneOrdreLivraisonVracBean = new LigneOrdreLivraisonVracBean();
			ligneOrdreLivraisonVracBean
					.setProduit(ordreLivraisonBean.getLigneOrdreLivraison().iterator().next().getProduit());
			ligneOrdreLivraisonVracBean
					.setQteCommande(ordreLivraisonBean.getLigneOrdreLivraison().iterator().next().getQteCommande());
			ordreLivraisonBean.setLigneOrdreLivraisonVrac(ligneOrdreLivraisonVracBean);
			ordreLivraisonBean.getLigneOrdreLivraison().clear();
			validateRequiredValuesLigneOLVRAC(ordreLivraisonBean);

			entity = (OrdreLivraison) mapper.mapStrict(ordreLivraisonBean, OrdreLivraison.class);
			try {

				if (isNew(entity)) { // if not on mode edit
					entity.setNumOL(codificationServiceImpl.getGeneratedCode(OrdreLivraison.class));
					save(entity);
					bonLivraisonService.createFromOL(entity).getOrdreLivraison().getId();
					return String.valueOf(entity.getId());
				} else {
					return String.valueOf(save(entity).getId());
				}

			} catch (DataIntegrityViolationException ex) {
				LOGGER.error(ex.getMessage());
				throw ex;
			}
		}

	}

	private void validateJourneeActiviteLivraison(OrdreLivraison ordreLivraison) {

		List<JourneeActiviteBean> journeeActivites = siteService
				.getAllJourneeActOuverteBySiteId(ordreLivraison.getSite().getId());
		if (journeeActivites.size() == 0 || journeeActivites.size() > 1) {
			throw new ServiceException(ErrorMessageType.ORDRE_LIVRAISON_JOURNEE_ACTIVITE_LIVRAISON_NO_EXISTS);
		}

		JourneeActiviteBean journeeActiviteBean = new JourneeActiviteBean();
		for (JourneeActiviteBean item : journeeActivites) {
			if (item.getActivite().equals(ActiviteJourneeActivite.LIVRAISON)) {
				journeeActiviteBean = item;
			}
		}
		if (journeeActiviteBean.getActivite() == null) {
			throw new ServiceException(ErrorMessageType.ORDRE_LIVRAISON_JOURNEE_ACTIVITE_LIVRAISON_NO_EXISTS);
		} else if (journeeActiviteBean.getDateJournee().after(ordreLivraison.getDateCommande())) {
			throw new ServiceException(ErrorMessageType.ORDRE_LIVRAISON_JOURNEE_ACTIVITE_DATE_COMMANDE_INVALID);
		}
		if (isNew(ordreLivraison)) {
			ordreLivraison.setJourneeActivite(mapper.mapStrict(journeeActiviteBean, JourneeActivite.class));
		} else {
			if (!journeeActiviteBean.getId().equals(ordreLivraison.getJourneeActivite().getId())) {
				throw new ServiceException(ErrorMessageType.ORDRE_LIVRAISON_JOURNEE_ACTIVITE_LIVRAISON_NO_CONFORM);
			}
		}
	}

	private void validateNumBcUnicity(OrdreLivraisonBean ordreLivraisonBean) {
		QOrdreLivraison ordreLivraison = QOrdreLivraison.ordreLivraison;
		Site site = (Site) mapper.map(ordreLivraisonBean.getSite(), Site.class);
		Client client = mapper.map(ordreLivraisonBean.getClient(), Client.class);
		BooleanExpression predicat = ordreLivraison.numBC.eq(ordreLivraisonBean.getNumBC())
				.and(ordreLivraison.site.eq(site)).and(ordreLivraison.client.eq(client))
				.and(ordreLivraison.statut.ne(StatutOrdreLivraison.ANNULE));

		if (ordreLivraisonBean.getId() != null) {
			predicat = predicat.and(ordreLivraison.id.ne(ordreLivraisonBean.getId()));
		}

		OrdreLivraison existedEntity = ordreLivraisonRepository.findOne(predicat);

		if (existedEntity != null) {
			throw new ServiceException(ErrorMessageType.ORDRE_LIVRAISON_NUMBC_ALREADY_EXISTS);
		}
	}

	private void validateRequiredValues(OrdreLivraisonBean ordreLivraisonBean) {
		if (StringUtils.isBlank(ordreLivraisonBean.getNumBC()) || ordreLivraisonBean.getDateCommande() == null
				|| ordreLivraisonBean.getDestination() == null || ordreLivraisonBean.getStatut() == null
				|| ordreLivraisonBean.getTypeChargement() == null
				|| (TypeChargement.CONDITIONNE.equals(ordreLivraisonBean.getTypeChargement())
						&& ordreLivraisonBean.getTypeBon() == null)
				|| ordreLivraisonBean.getClient() == null || ordreLivraisonBean.getChauffeur() == null
				|| ordreLivraisonBean.getCamion() == null || ordreLivraisonBean.getSite() == null
				|| ordreLivraisonBean.getConcessionnaire() == null) {

			throw new ServiceException(ErrorMessageType.ORDRE_LIVRAISON_MISSING_REQUIRED_VALUES);
		}

	}

	private void validateRequiredValuesLigneOLCond(OrdreLivraisonBean ordreLivraisonBean) {

		Set<LigneOrdreLivraisonBean> ligneOrdreLivraisons = ordreLivraisonBean.getLigneOrdreLivraison();
		if (ligneOrdreLivraisons.size() <= 0) {
			throw new ServiceException(ErrorMessageType.ORDRE_LIVRAISON_MISSING_LIGNE_ORDRE_LIVRAISON);
		}
		for (LigneOrdreLivraisonBean ligneOrdreLivraison : ligneOrdreLivraisons) {
			ligneOrdreLivraisonService.validateLigneOrdreLivraisonBean(ligneOrdreLivraison);
		}
	}

	private void validateRequiredValuesLigneOLVRAC(OrdreLivraisonBean ordreLivraisonBean) {

		LigneOrdreLivraisonVracBean ligneOrdreLivraisonVrac = ordreLivraisonBean.getLigneOrdreLivraisonVrac();
		if (ligneOrdreLivraisonVrac == null) {
			throw new ServiceException(ErrorMessageType.ORDRE_LIVRAISON_MISSING_LIGNE_ORDRE_LIVRAISON_VRAC);
		}
		if (ordreLivraisonBean.getCiterne() == null) {
			throw new ServiceException(ErrorMessageType.ORDRE_LIVRAISON_MISSING_CITERNE_VALUE);
		}
		ligneOLVRACService.validateLigneOLVRACBean(ligneOrdreLivraisonVrac);
	}

	@Override
	public GridListBean<OrdreLivraisonBean> list(PageDataBean paginateData) {
		return ordreLivraisonRepository.findEntities(paginateData, getColumnDef());
	}

	@Override
	@Transactional
	public OrdreLivraisonBean getDetails(Long id) {

		OrdreLivraison ordreLivraison = findOne(id);
		OrdreLivraisonBean ordreLivraisonBean = mapper.map(ordreLivraison, OrdreLivraisonBean.class);
		ordreLivraisonBean.setDateCommande(ordreLivraison.getDateCommande());
		return ordreLivraisonBean;
	}

	public Map<String, ColumnDef> getColumnDef() {
		return columnDefService.getColumnDefMapByViewName(ViewName.ORDRE_LIVRAISON);
	}

	@Override
	@Transactional
	public List<OrdreLivraisonBean> getAll() {
		List<OrdreLivraison> ordreLivraisons = findAll();
		return mapper.map(ordreLivraisons, OrdreLivraisonBean.LIST_BEAN_TYPE);
	}

	@Override
	@Transactional
	public Boolean isAnyOrdreNotValide(Long id) {
		return ordreLivraisonRepository.findAll(QOrdreLivraison.ordreLivraison.statut.ne(StatutOrdreLivraison.VALIDE)
				.and(QOrdreLivraison.ordreLivraison.journeeActivite.id.eq(id))).iterator().hasNext();
	}

	@Override
	@Transactional
	public Boolean existeBonLivraison(OrdreLivraison ordre) {
		QBonLivraison bon = QBonLivraison.bonLivraison;
		BonLivraison bonliv = bonLivraisonRepository.findOne(bon.id.eq(ordre.getId()));
		if (bonliv == null) {
			return false;
		} else {
			return true;
		}

	}

	@Override
	public void updatefromFileAttente(OrdreLivraisonBean ordreLivraisonBean) {

		OrdreLivraison entity = (OrdreLivraison) mapper.mapStrict(ordreLivraisonBean, OrdreLivraison.class);

		try {
			save(entity);
		} catch (DataIntegrityViolationException ex) {
			LOGGER.error(ex.getMessage());
			throw ex;
		}
	}

}
