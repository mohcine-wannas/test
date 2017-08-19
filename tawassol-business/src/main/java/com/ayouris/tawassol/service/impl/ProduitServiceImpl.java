package com.ayouris.tawassol.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.ayouris.tawassol.admin.model.enums.ViewName;
import com.ayouris.tawassol.common.exception.ErrorMessageType;
import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.bean.ProduitBean;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.Produit;
import com.ayouris.tawassol.common.model.entity.QProduit;
import com.ayouris.tawassol.common.model.enums.TypeChargement;
import com.ayouris.tawassol.repository.ProduitRepository;
import com.ayouris.tawassol.service.ColumnDefService;
import com.ayouris.tawassol.service.ProduitService;
import com.ayouris.tawassol.service.ServiceException;

/**
 * 
 * @author m.wannas
 *
 */

@Service
public class ProduitServiceImpl extends GenericServiceImpl<Produit, Long> implements ProduitService {

	@Autowired
	private ProduitRepository produitRepository;

	@Autowired
	private ColumnDefService columnDefService;

	@Autowired
	private CustomModelMapper mapper;

	@Override
	public Long createOrUpdate(ProduitBean produitBean) {
		validateRequiredValues(produitBean);
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Produit entity = (Produit) mapper.map(produitBean, Produit.class);
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);

		try {
			return save(entity).getId();
		} catch (DataIntegrityViolationException ex) {
			throw new ServiceException(ErrorMessageType.PRODUIT_CODE_ALREADY_EXISTS);
		}
	}

	private void validateRequiredValues(ProduitBean produitBean) {
		if (StringUtils.isBlank(produitBean.getCode()) || StringUtils.isBlank(produitBean.getLibelle())
				|| produitBean.getPoids() == null || produitBean.getNatureProduit() == null
				|| produitBean.getTypeChargement() == null) {
			throw new ServiceException(ErrorMessageType.PRODUIT_MISSING_REQUIRED_VALUES);
		}
	}

	@Override
	public GridListBean<ProduitBean> list(PageDataBean paginateData) {
		return produitRepository.findEntities(paginateData, getColumnDef());
	}

	@Override
	public ProduitBean getDetails(Long id) {
		Produit produit = findOne(id);
		return mapper.map(produit, ProduitBean.class);
	}

	public Map<String, ColumnDef> getColumnDef() {
		return columnDefService.getColumnDefMapByViewName(ViewName.PRODUIT);
	}

	@Override
	public List<ProduitBean> getAll() {
		List<Produit> produits = findAll();
		return mapper.map(produits, ProduitBean.LIST_BEAN_TYPE);
	}

	@Override
	public List<ProduitBean> getAllProduitConditionne() {
		return mapper.map(produitRepository.findAll(
				QProduit.produit.typeChargement.eq(TypeChargement.CONDITIONNE).and(QProduit.produit.active.isTrue())),
				ProduitBean.LIST_BEAN_TYPE);
	}

	@Override
	public List<ProduitBean> getAllProduitActif() {
		return mapper.map(produitRepository.findAll(QProduit.produit.active.isTrue()), ProduitBean.LIST_BEAN_TYPE);
	}

}
