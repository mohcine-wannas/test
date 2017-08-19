package com.ayouris.tawassol.service.impl;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayouris.tawassol.admin.model.beans.ColumnDefBean;
import com.ayouris.tawassol.admin.model.enums.ViewName;
import com.ayouris.tawassol.common.exception.ErrorMessageType;
import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.BanqueBean;
import com.ayouris.tawassol.common.model.bean.BanqueListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.entity.Banque;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.QBanque;
import com.ayouris.tawassol.repository.BanqueRepository;
import com.ayouris.tawassol.service.BanqueService;
import com.ayouris.tawassol.service.ColumnDefService;
import com.ayouris.tawassol.service.ServiceException;

/**
 * Created by YounesM on 05/05/2017.
 */

@Service
public class BanqueServiceImpl extends GenericServiceImpl<Banque, Long> implements BanqueService {

	@Autowired
	private BanqueRepository banqueRepository;

	@Autowired
	private ColumnDefService columnDefService;

	@Autowired
	private CustomModelMapper mapper;

	@Override
	public Long createOrUpdate(BanqueBean banqueBean) {
		validateRequiredValues(banqueBean);

		Banque entity = mapper.map(banqueBean, Banque.class);
		return save(entity).getId();
	}

	private void validateRequiredValues(BanqueBean banqueBean) {
		if (StringUtils.isBlank(banqueBean.getLibelle()) || StringUtils.isBlank(banqueBean.getAbreviation())) {
			throw new ServiceException(ErrorMessageType.BANQUE_MISSING_REQUIRED_VALUES);
		}
	}

	@Override
	public BanqueListBean list(PageDataBean paginateData) {
		return banqueRepository.findBanques(paginateData, getColumnDef());
	}

	@Override
	public BanqueBean getDetails(Long id) {
		Banque banque = findOne(id);
		return mapper.map(banque, BanqueBean.class);
	}

	public Map<String, ColumnDef> getColumnDef() {
		List<ColumnDefBean> columnDefBeans = columnDefService.getByViewName(ViewName.BANQUE);

		List<ColumnDef> columnDefs = mapper.map(columnDefBeans, ColumnDefBean.LIST_ENTITY_TYPE);

		Map<String, ColumnDef> columnDefMap = columnDefs.stream()
				.collect(Collectors.toMap(ColumnDef::getField, Function.identity()));

		return columnDefMap;
	}

	@Override
	public List<BanqueBean> getAll() {
		List<Banque> banques = (List<Banque>) banqueRepository.findAll(QBanque.banque.active.isTrue());
		return mapper.map(banques, BanqueBean.LIST_BEAN_TYPE);
	}
}
