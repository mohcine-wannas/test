package com.ayouris.tawassol.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayouris.tawassol.admin.model.enums.ViewName;
import com.ayouris.tawassol.common.exception.ErrorMessageType;
import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.bean.TypePieceReglementBean;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.QTypePieceReglement;
import com.ayouris.tawassol.common.model.entity.TypePieceReglement;
import com.ayouris.tawassol.repository.TypePieceReglementRepository;
import com.ayouris.tawassol.service.ColumnDefService;
import com.ayouris.tawassol.service.ServiceException;
import com.ayouris.tawassol.service.TypePieceReglementService;

/**
 * 
 * @author m.wannas
 *
 */

@Service
public class TypePieceReglementServiceImpl extends GenericServiceImpl<TypePieceReglement, Long>
		implements TypePieceReglementService {

	@Autowired
	private TypePieceReglementRepository typePieceReglementRepository;

	@Autowired
	private ColumnDefService columnDefService;

	@Autowired
	private CustomModelMapper mapper;

	@Override
	public Long createOrUpdate(TypePieceReglementBean typePieceReglementBean) {
		validateRequiredValues(typePieceReglementBean);
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		TypePieceReglement entity = (TypePieceReglement) mapper.map(typePieceReglementBean, TypePieceReglement.class);
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
		return save(entity).getId();

	}

	private void validateRequiredValues(TypePieceReglementBean typePieceReglementBean) {
		if (StringUtils.isBlank(typePieceReglementBean.getLibelle())) {
			throw new ServiceException(ErrorMessageType.TYPE_PIECE_REGLEMENT_MISSING_REQUIRED_VALUES);
		}
	}

	@Override
	public GridListBean<TypePieceReglementBean> list(PageDataBean paginateData) {
		return typePieceReglementRepository.findEntities(paginateData, getColumnDef());
	}

	@Override
	public TypePieceReglementBean getDetails(Long id) {
		TypePieceReglement pieceReglement = findOne(id);
		return mapper.map(pieceReglement, TypePieceReglementBean.class);
	}

	public Map<String, ColumnDef> getColumnDef() {
		return columnDefService.getColumnDefMapByViewName(ViewName.PIECE_REGLEMENT);
	}

	@Override
	public List<TypePieceReglementBean> getAll() {
		List<TypePieceReglement> pieceReglements = (List<TypePieceReglement>) typePieceReglementRepository
				.findAll(QTypePieceReglement.typePieceReglement.active.isTrue());
		return mapper.map(pieceReglements, TypePieceReglementBean.LIST_BEAN_TYPE);
	}

}
