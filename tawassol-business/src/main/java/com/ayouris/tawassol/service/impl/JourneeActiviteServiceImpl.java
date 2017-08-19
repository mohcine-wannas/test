package com.ayouris.tawassol.service.impl;

import java.util.List;
import java.util.Map;

import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.ayouris.tawassol.admin.model.enums.ViewName;
import com.ayouris.tawassol.common.exception.ErrorMessageType;
import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.JourneeActiviteBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.JourneeActivite;
import com.ayouris.tawassol.common.model.entity.Site;
import com.ayouris.tawassol.common.model.enums.ActiviteJourneeActivite;
import com.ayouris.tawassol.common.model.enums.StatutJourneeActivite;
import com.ayouris.tawassol.repository.JourneeActiviteRepository;
import com.ayouris.tawassol.security.utils.SecurityUtils;
import com.ayouris.tawassol.service.BonLivraisonService;
import com.ayouris.tawassol.service.ColumnDefService;
import com.ayouris.tawassol.service.FicheMarcheService;
import com.ayouris.tawassol.service.JourneeActiviteService;
import com.ayouris.tawassol.service.OrdreLivraisonService;
import com.ayouris.tawassol.service.ServiceException;
import com.ayouris.tawassol.service.SiteService;

@Service
public class JourneeActiviteServiceImpl extends GenericServiceImpl<JourneeActivite, Long>
		implements JourneeActiviteService {

	@Autowired
	private JourneeActiviteRepository journeeActiviteRepository;

	@Autowired
	private FicheMarcheService ficheMarcheService;

	@Autowired
	private BonLivraisonService bonLivraisonService;

	@Autowired
	private OrdreLivraisonService ordreLivraisonService;

	@Autowired
	private SiteService siteService;

	@Autowired
	private ColumnDefService columnDefService;

	@Autowired
	private CustomModelMapper mapper;

	@Override
	public Long createOrUpdate(JourneeActiviteBean journeeActiviteBean) {
		validateRequiredValues(journeeActiviteBean);

		if (journeeActiviteBean.getId() == null) {
			validateJourneeActivite(journeeActiviteBean);
		} else {
			if (ActiviteJourneeActivite.EMPLISSAGE.equals(journeeActiviteBean.getActivite())
					&& ficheMarcheService.isAllFichesNotValide(journeeActiviteBean.getId())) {
				throw new ServiceException(ErrorMessageType.JOURNEE_ACTIVITE_CLOTURE_INVALID);
			} else if (ActiviteJourneeActivite.LIVRAISON.equals(journeeActiviteBean.getActivite())
					&& (ordreLivraisonService.isAnyOrdreNotValide(journeeActiviteBean.getId()) || bonLivraisonService.isAnyBlNotValide(journeeActiviteBean.getId()))) {
				throw new ServiceException(ErrorMessageType.JOURNEE_ACTIVITE_CLOTURE_INVALID);
			}
		}

		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		JourneeActivite entity = (JourneeActivite) mapper.map(journeeActiviteBean, JourneeActivite.class);
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);

		try {
			return save(entity).getId();
		} catch (DataIntegrityViolationException ex) {
			throw new ServiceException(ErrorMessageType.JOURNEE_ACTIVITE_CODE_ALREADY_EXISTS);
		}
	}

	private void validateRequiredValues(JourneeActiviteBean journeeActiviteBean) {
		if (journeeActiviteBean.getActivite() == null || journeeActiviteBean.getSite() == null
				|| journeeActiviteBean.getDateJournee() == null) {

			throw new ServiceException(ErrorMessageType.JOURNEE_ACTIVITE_MISSING_REQUIRED_VALUES);
		}
	}

	private void validateJourneeActivite(JourneeActiviteBean journeeActiviteBean) {

		List<JourneeActiviteBean> journeeActivites = siteService
				.getAllJourneeActOuverteBySiteId(journeeActiviteBean.getSite().getId());
		for (JourneeActiviteBean item : journeeActivites) {
			if (journeeActiviteBean.getActivite().equals(item.getActivite())) {
				throw new ServiceException(ErrorMessageType.JOURNEE_ACTIVITE_ACTIVITE_ALREADY_EXISTS);
			}
			if (journeeActiviteBean.getActivite().equals(item.getActivite())) {
				throw new ServiceException(ErrorMessageType.JOURNEE_ACTIVITE_ACTIVITE_ALREADY_EXISTS);
			}
		}
	}

	@Override
	public GridListBean<JourneeActiviteBean> list(PageDataBean paginateData) {
		return journeeActiviteRepository.findEntities(paginateData, getColumnDef());
	}

	@Override
	public JourneeActiviteBean getDetails(Long id) {
		JourneeActivite journeeActivite = findOne(id);
		return mapper.map(journeeActivite, JourneeActiviteBean.class);
	}

	public Map<String, ColumnDef> getColumnDef() {
		return columnDefService.getColumnDefMapByViewName(ViewName.JOURNEE_ACTIVITE);
	}

	@Override
	public List<JourneeActiviteBean> getAll() {
		List<JourneeActivite> journeeActivites = findAll();
		return mapper.map(journeeActivites, JourneeActiviteBean.LIST_BEAN_TYPE);
	}

	@Override
	public JourneeActivite getCurrentJourneeActiviteOuvertByActivity(ActiviteJourneeActivite activite) {
		Site currentSite = SecurityUtils.getCurrentUserSite();

		return journeeActiviteRepository.findBySiteAndActiviteAndStatut(currentSite, activite,
				StatutJourneeActivite.OUVERTE);
	}

}
