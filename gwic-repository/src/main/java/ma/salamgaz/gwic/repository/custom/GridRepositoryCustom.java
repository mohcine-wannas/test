package ma.salamgaz.gwic.repository.custom;

import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import ma.salamgaz.gwic.common.model.bean.GridListBean;
import ma.salamgaz.gwic.common.model.bean.PageDataBean;
import ma.salamgaz.gwic.common.model.entity.ColumnDef;

/**
 * 
 * @author m.wannas
 *
 */
public interface GridRepositoryCustom<T> {
	
	@Transactional
    GridListBean<T> findEntities(PageDataBean paginateData, Map<String, ColumnDef> columnDefMap);
}
