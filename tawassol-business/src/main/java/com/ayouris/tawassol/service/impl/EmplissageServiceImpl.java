package com.ayouris.tawassol.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayouris.tawassol.admin.model.entity.User;
import com.ayouris.tawassol.admin.model.enums.ViewName;
import com.ayouris.tawassol.common.exception.ErrorMessageType;
import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.EmplissageBean;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.Emplissage;
import com.ayouris.tawassol.common.model.entity.EmplissageResultControl;
import com.ayouris.tawassol.common.model.entity.FicheMarche;
import com.ayouris.tawassol.common.model.entity.QEmplissage;
import com.ayouris.tawassol.common.model.enums.StatutEmplissage;
import com.ayouris.tawassol.repository.EmplissageRepository;
import com.ayouris.tawassol.security.utils.SecurityUtils;
import com.ayouris.tawassol.service.ColumnDefService;
import com.ayouris.tawassol.service.EmplissageResultControlService;
import com.ayouris.tawassol.service.EmplissageService;
import com.ayouris.tawassol.service.FicheMarcheService;
import com.ayouris.tawassol.service.ServiceException;

@Service
public class EmplissageServiceImpl extends GenericServiceImpl<Emplissage, Long> implements EmplissageService {

	@Autowired
	private EmplissageRepository emplissageRepository;

	@Autowired
	private FicheMarcheService ficheMarcheService;

	@Autowired
	private EmplissageResultControlService emplissageResultControlService;

	@Autowired
	private CustomModelMapper mapper;

	@Autowired
	private CodificationServiceImpl codificationServiceImpl = new CodificationServiceImpl();

	@Autowired
	private ColumnDefService columnDefService;

	private static final Logger LOGGER = LoggerFactory.getLogger(EmplissageServiceImpl.class);

	@Override
	public EmplissageBean getControleEmplissage(Long id) {
		Emplissage emplissage = findOne(id);
		Map<Long, Map<Long, Long>> matriceEmplissageResultat = new LinkedHashMap<Long, Map<Long, Long>>();

		List<EmplissageResultControl> results = emplissageResultControlService.getAllLineByIdEmplissage(id);
		if (!results.isEmpty()) {
			Long ligne = results.get(0).getEmplissageControlLigne().getId();
			matriceEmplissageResultat.put(ligne, emplissageResultControlService.getAllColumnControlByIdLine(id, ligne));

			for (EmplissageResultControl emp : results) {
				if (emp.getEmplissageControlLigne().getId() != ligne) {
					ligne = emp.getEmplissageControlLigne().getId();
					matriceEmplissageResultat.put(ligne,
							emplissageResultControlService.getAllColumnControlByIdLine(id, ligne));
				}
			}
		}

		emplissage.getConcessionnaire().setConditionCommerciale(null);
		EmplissageBean emplissageBean = mapper.map(emplissage, EmplissageBean.class);
		emplissageBean.setMatriceEmplissageResultat(matriceEmplissageResultat);
		return emplissageBean;
	}

	@Override
	public Long updateEmplissageFromControl(EmplissageBean emplissageBean) {

		Emplissage entity = null;
		validateRequiredValues(emplissageBean);
		validateRequiredValuesOfControl(emplissageBean);
		validatePositiveValues(emplissageBean);
		validatePositiveValuesOfControl(emplissageBean);
		validateFicheJournaliere(emplissageBean);
		emplissageBean.setStatut(StatutEmplissage.VALIDE);
		entity = (Emplissage) mapper.mapStrict(emplissageBean, Emplissage.class);
		try {
			save(entity);
			emplissageResultControlService.update(emplissageBean);
			return entity.getId();
		} catch (DataIntegrityViolationException ex) {
			LOGGER.error(ex.getMessage());
			throw ex;
		}
	}

	@Override
	@Transactional
	public Long create(EmplissageBean emplissageBean) {

		Emplissage entity = null;
		User currentUser = SecurityUtils.getCurrentUser();
		validateRequiredValues(emplissageBean);
		validatePositiveValues(emplissageBean);
		validateFicheJournaliere(emplissageBean);

		entity = (Emplissage) mapper.mapStrict(emplissageBean, Emplissage.class);
		FicheMarche ficheMarche = ficheMarcheService.getFichesNotValideByUsername(emplissageBean, currentUser);

		if (isNew(entity)) {
			entity.setNumOE(codificationServiceImpl.getGeneratedCode(Emplissage.class));
			ficheMarche.setQteEmplissageTotal(ficheMarche.getQteEmplissageTotal() + entity.getQteEmplissage());
		} else {
			Emplissage oldEntity = emplissageRepository.findOne(entity.getId());
			ficheMarche.setQteEmplissageTotal(
					ficheMarche.getQteEmplissageTotal() + entity.getQteEmplissage() - oldEntity.getQteEmplissage());
			entity.setNumOE(oldEntity.getNumOE());
		}
		updateCadences(ficheMarche);

		entity.setFicheMarche(ficheMarche);
		try {
			save(entity);
			return entity.getId();
		} catch (DataIntegrityViolationException ex) {
			LOGGER.error(ex.getMessage());
			throw ex;
		}
	}


	public void updateCadences(FicheMarche ficheMarche) {
		
		Calendar postDeb = Calendar.getInstance();
		postDeb.setTime(ficheMarche.getPost().getHeureDebut());
		postDeb.clear(Calendar.SECOND);
		postDeb.clear(Calendar.MILLISECOND);
		
		Calendar postFin = Calendar.getInstance();
		postFin.setTime(ficheMarche.getPost().getHeureFin());
		postFin.clear(Calendar.SECOND);
		postFin.clear(Calendar.MILLISECOND);
		
		Calendar pause = Calendar.getInstance();
		pause.setTime(ficheMarche.getTempsPause());
		pause.clear(Calendar.SECOND);
		pause.clear(Calendar.MILLISECOND);
		
		Date temps = new Date(postFin.getTimeInMillis()- postDeb.getTimeInMillis() - pause.getTimeInMillis());
		
		DateTime tempsJoda = new DateTime(temps);
		Long heure = (long) (tempsJoda.getMinuteOfDay() * 60000);
		Double cadence = (double) ficheMarche.getQteEmplissageTotal() * 3600000 / (double) heure;
		ficheMarche.setCadenceArretsCompris(cadence);

		tempsJoda = new DateTime(ficheMarche.getTempsEffectif());
		heure = (long) (tempsJoda.getMinuteOfDay() * 60000);
		cadence = (double) ficheMarche.getQteEmplissageTotal() * 3600000 / (double) heure;
		ficheMarche.setCadenceHorsArrets(cadence);
	}

	private void validateFicheJournaliere(EmplissageBean emplissageBean) {
		User currentUser = SecurityUtils.getCurrentUser();
		FicheMarche ficheMarche = ficheMarcheService.getFichesNotValideByUsername(emplissageBean,
				currentUser);
		if (ficheMarche==null) {
			throw new ServiceException(ErrorMessageType.FICHE_MARCHE_NO_EXISTS);
		}

	}

	private void validateRequiredValues(EmplissageBean emplissageBean) {
		if (emplissageBean.getStatut() == null || emplissageBean.getOrigine() == null
				|| emplissageBean.getClient() == null || emplissageBean.getConcessionnaire() == null
				|| emplissageBean.getProduit() == null || emplissageBean.getSite() == null
				|| emplissageBean.getQteEmplissage() == null) {

			throw new ServiceException(ErrorMessageType.EMPLISSAGE_MISSING_REQUIRED_VALUES);
		}

	}

	private void validateRequiredValuesOfControl(EmplissageBean emplissageBean) {
		if (emplissageBean.getHeureDebut() == null || emplissageBean.getHeureFin() == null
				|| emplissageBean.getQteBDLavee() == null || emplissageBean.getCadence() == null
				|| emplissageBean.getBdControle() == null || emplissageBean.getBdDefect() == null
				|| emplissageBean.getBdAReparer() == null) {

			throw new ServiceException(ErrorMessageType.EMPLISSAGE_MISSING_REQUIRED_VALUES);
		}

	}

	private void validatePositiveValues(EmplissageBean emplissageBean) {
		if (emplissageBean.getQteEmplissage() <= 0) {
			throw new ServiceException(ErrorMessageType.EMPLISSAGE_QTE_EMPLISSAGE_POSITIVE);
		}
	}

	private void validatePositiveValuesOfControl(EmplissageBean emplissageBean) {
		if (emplissageBean.getQteBDLavee() < 0 || emplissageBean.getQteBDLavee() < 0
				|| emplissageBean.getCadence() < 0 || emplissageBean.getBdControle() < 0
				|| emplissageBean.getBdDefect() < 0 || emplissageBean.getBdAReparer() < 0) {
			throw new ServiceException(ErrorMessageType.EMPLISSAGE_QTE_AND_BD_POSITIVE);
		}
	}

	@Override
	public GridListBean<EmplissageBean> list(PageDataBean paginateData) {
		return emplissageRepository.findEntities(paginateData, getColumnDef());
	}

	@Override
	@Transactional
	public EmplissageBean getDetails(Long id) {
		Emplissage emplissage = findOne(id);
		return mapper.map(emplissage, EmplissageBean.class);
	}

	@Override
	public Boolean getAllEmpNotValide() {
		return emplissageRepository.findAll(QEmplissage.emplissage.statut.ne(StatutEmplissage.VALIDE)).iterator()
				.hasNext();
	}

	@Override
	public Boolean isAllEmpNotValideFicheMarche(Long id) {
		return emplissageRepository.findAll(QEmplissage.emplissage.statut.ne(StatutEmplissage.VALIDE)
				.and(QEmplissage.emplissage.ficheMarche.id.eq(id))).iterator().hasNext();
	}

	public Map<String, ColumnDef> getColumnDef() {
		return columnDefService.getColumnDefMapByViewName(ViewName.EMPLISSAGE);
	}

	@Override
	@Transactional
	public List<EmplissageBean> getAll() {
		List<Emplissage> emplissages = findAll();
		return mapper.map(emplissages, EmplissageBean.LIST_BEAN_TYPE);
	}
}
