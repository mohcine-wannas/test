package ma.salamgaz.tawassol.repository.custom;

import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import ma.salamgaz.tawassol.common.model.bean.GridListBean;
import ma.salamgaz.tawassol.common.model.bean.PageDataBean;
import ma.salamgaz.tawassol.common.model.entity.ColumnDef;

/**
 * 
 * @author m.wannas
 *
 */
public interface GridRepositoryCustom<T> {
	
	@Transactional
    GridListBean<T> findEntities(PageDataBean paginateData, Map<String, ColumnDef> columnDefMap);
}
