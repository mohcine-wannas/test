package ma.salamgaz.tawassol.service.impl;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ma.salamgaz.tawassol.admin.model.beans.ColumnDefBean;
import ma.salamgaz.tawassol.admin.model.enums.ViewName;
import ma.salamgaz.tawassol.common.mapper.CustomModelMapper;
import ma.salamgaz.tawassol.common.model.entity.ColumnDef;
import ma.salamgaz.tawassol.common.model.entity.QColumnDef;
import ma.salamgaz.tawassol.repository.impl.ColumnDefRepository;
import ma.salamgaz.tawassol.service.ColumnDefService;


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
        List<ColumnDefBean> columnDefBeans = getByViewName(viewnName);

        List<ColumnDef> columnDefs = mapper.map(columnDefBeans, ColumnDefBean.LIST_ENTITY_TYPE);

        Map<String, ColumnDef> columnDefMap = columnDefs.stream().collect(
                Collectors.toMap(ColumnDef::getField, Function.identity()));

        return columnDefMap;
	}
	
	

}
