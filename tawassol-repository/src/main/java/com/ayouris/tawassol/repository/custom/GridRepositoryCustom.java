package com.ayouris.tawassol.repository.custom;

import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.entity.ColumnDef;

/**
 * 
 * @author m.wannas
 *
 */
public interface GridRepositoryCustom<T> {
	
	@Transactional
    GridListBean<T> findEntities(PageDataBean paginateData, Map<String, ColumnDef> columnDefMap);
}
