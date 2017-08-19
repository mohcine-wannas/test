package com.ayouris.tawassol.service.impl;

import java.util.List;
import java.util.Map;

import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ayouris.tawassol.admin.model.enums.ViewName;
import com.ayouris.tawassol.common.exception.ErrorMessageType;
import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.AffectationProduitListOperateurBean;
import com.ayouris.tawassol.common.model.bean.AffectationProduitOperateurBean;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.entity.AffectationProduitOperateur;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.repository.AffectationProduitOperateurRepository;
import com.ayouris.tawassol.service.AffectationProduitOperateurService;
import com.ayouris.tawassol.service.ColumnDefService;
import com.ayouris.tawassol.service.ServiceException;

/**
 * 
 * @author m.wannas
 *
 */

@Service
public class AffectationProduitOperateurServiceImpl extends GenericServiceImpl<AffectationProduitOperateur, Long>
		implements AffectationProduitOperateurService {

	@Autowired
	private AffectationProduitOperateurRepository affectationProduitOperateurRepository;

	@Autowired
	private ColumnDefService columnDefService;

	@Autowired
	private CustomModelMapper mapper;

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Long createOrUpdate(AffectationProduitOperateurBean affectationProduitOperateurBean) {
		validateRequiredValues(affectationProduitOperateurBean);
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		AffectationProduitOperateur entity = (AffectationProduitOperateur) mapper.map(affectationProduitOperateurBean,
				AffectationProduitOperateur.class);
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
		return save(entity).getId();
	}

	private void validateRequiredValues(AffectationProduitOperateurBean affectationProduitOperateurBean) {
		if (affectationProduitOperateurBean.getUser() == null 
				|| affectationProduitOperateurBean.getProduit() == null
				|| affectationProduitOperateurBean.getSite() == null) {
			throw new ServiceException(ErrorMessageType.TYPE_FILE_ATTENTE_MISSING_REQUIRED_VALUES);
		}
	}

	@Override
	public GridListBean<AffectationProduitOperateurBean> list(PageDataBean paginateData) {
		return affectationProduitOperateurRepository.findEntities(paginateData, getColumnDef());
	}

	@Override
	public AffectationProduitOperateurBean getDetails(Long id) {
		AffectationProduitOperateur affectationProduitOperateur = findOne(id);
		return mapper.map(affectationProduitOperateur, AffectationProduitOperateurBean.class);
	}

	public Map<String, ColumnDef> getColumnDef() {
		return columnDefService.getColumnDefMapByViewName(ViewName.AFFECTATION_PRODUIT_OPERATEUR);
	}

	@Override
	public List<AffectationProduitOperateurBean> getAll() {
		List<AffectationProduitOperateur> affectationProduitOperateurs = findAll();
		return mapper.map(affectationProduitOperateurs, AffectationProduitOperateurBean.LIST_BEAN_TYPE);
	}

	@Override
	@Transactional
	public Object findBySiteIdAndUserId(Long idSite, Long idUser) {
		List<AffectationProduitOperateur> affectationProduitOperateurs = affectationProduitOperateurRepository.findBySiteIdAndUserId(idSite, idUser);
		return mapper.map(affectationProduitOperateurs, AffectationProduitOperateurBean.LIST_BEAN_TYPE);

	}

	@Override
	public String createOrUpdate(AffectationProduitListOperateurBean object) {
		removeBySiteIdAndUserId(object.getSite().getId(),object.getUser().getId());
		List<AffectationProduitOperateurBean> affectationProduitOperateurBeans = object.convertToAffectationProduitOperateurBeans();
		for (AffectationProduitOperateurBean affectationProduitOperateurBean : affectationProduitOperateurBeans) {
			createOrUpdate(affectationProduitOperateurBean);
		}
		return object.getSite().getId() + "+" + object.getUser().getId();
		
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void removeBySiteIdAndUserId(Long idSite, Long idUser) {
		List<AffectationProduitOperateur> affectations = affectationProduitOperateurRepository.findBySiteIdAndUserId(idSite,idUser);
		for (AffectationProduitOperateur affectationProduitOperateur : affectations) {
			delete(affectationProduitOperateur);
		}
	}

}
