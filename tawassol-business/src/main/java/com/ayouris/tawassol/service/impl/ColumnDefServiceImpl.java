package com.ayouris.tawassol.service.impl;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayouris.tawassol.admin.model.beans.ColumnDefBean;
import com.ayouris.tawassol.admin.model.enums.ViewName;
import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.QColumnDef;
import com.ayouris.tawassol.repository.impl.ColumnDefRepository;
import com.ayouris.tawassol.service.ColumnDefService;


@Service
@Transactional
public class ColumnDefServiceImpl implements ColumnDefService {

    @Autowired
    private CustomModelMapper mapper;
    
    @Autowired
    private ColumnDefRepository columnDefRepository;

	@Override
	public List<ColumnDefBean> getByViewName(ViewName viewnName) {
		Iterable<ColumnDef> columnDefs = columnDefRepository.findAll(QColumnDef.columnDef.viewName.eq(viewnName));
		return ColumnDefBean.convert(columnDefs);
	}
	
	@Override
	public List<ColumnDefBean> getNoHiddenByViewName(ViewName viewnName) {
		QColumnDef columndef = QColumnDef.columnDef;
		Iterable<ColumnDef> columnDefs = columnDefRepository.findAll(columndef.viewName.eq(viewnName)
				.and(columndef.hide.ne(true)));
		return ColumnDefBean.convert(columnDefs);
	}

	@Override
	public Map<String, ColumnDef> getColumnDefMapByViewName(ViewName viewnName) {
		
		Iterable<ColumnDef> columnDefsIterable = columnDefRepository.findAll(QColumnDef.columnDef.viewName.eq(viewnName));
        List<ColumnDef> columnDefs = mapper.map(columnDefsIterable, ColumnDefBean.LIST_ENTITY_TYPE);

        Map<String, ColumnDef> columnDefMap = columnDefs.stream().collect(
                Collectors.toMap(ColumnDef::getField, Function.identity()));

        return columnDefMap;
	}
	
	

}
