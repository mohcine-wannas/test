package ma.salamgaz.gwic.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import ma.salamgaz.gwic.common.mapper.CustomModelMapper;
import ma.salamgaz.gwic.common.model.bean.ControleCamionBean;
import ma.salamgaz.gwic.common.model.bean.GridListBean;
import ma.salamgaz.gwic.common.model.bean.PageDataBean;
import ma.salamgaz.gwic.common.model.entity.ColumnDef;
import ma.salamgaz.gwic.common.model.entity.ControleCamion;
import ma.salamgaz.gwic.common.model.entity.QControleCamion;
import ma.salamgaz.gwic.common.util.QueryUtils;
import ma.salamgaz.gwic.repository.custom.ControleCamionRepositoryCustom;
import ma.salamgaz.gwic.repository.custom.GridRepositoryCustom;

/**
 * 
 * @author m.wannas
 *
 */
public class ControleCamionRepositoryImpl extends ListRepositoryImpl<ControleCamion,QControleCamion>  implements ControleCamionRepositoryCustom, GridRepositoryCustom<ControleCamionBean> {

	  @Autowired
	    private CustomModelMapper mapper;
	    private final JPAQueryFactory queryFactory;

	    @Autowired
	    public ControleCamionRepositoryImpl(JPAQueryFactory queryFactory) {
	        this.queryFactory = queryFactory;
	    }

	    @Override
	    public GridListBean<ControleCamionBean> findEntities(PageDataBean pageDataBean, Map<String, ColumnDef> columnDefMap){
	        QControleCamion controleCamions = QControleCamion.controleCamion;

	        JPAQuery<ControleCamion> query = queryFactory.selectFrom(controleCamions);

	        // Sorting
	        applySort(query, pageDataBean, controleCamions, columnDefMap);

	        // Filtering
	        applyFilterAndQuickSearch(query, pageDataBean, controleCamions, columnDefMap);

	        // Count
	        Long totalCount = Long.valueOf(query.fetchCount());

	        // Pagination
	        QueryUtils.applyPagination(query, pageDataBean.getPageNumber(), pageDataBean.getPageSize());

	        // Result
	        List<ControleCamion> controleCamionList = query.fetch();

	        GridListBean<ControleCamionBean> result = new GridListBean<ControleCamionBean>();

	        result.setTotalItems(totalCount);

	        List<ControleCamionBean> banqueBeans = mapper.map(controleCamionList, ControleCamionBean.LIST_BEAN_TYPE);
	        result.getData().addAll(banqueBeans);

	        return result;
	    }

}
