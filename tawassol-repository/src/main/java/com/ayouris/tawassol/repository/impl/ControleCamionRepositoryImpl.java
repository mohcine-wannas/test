package com.ayouris.tawassol.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.ControleCamionBean;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.ControleCamion;
import com.ayouris.tawassol.common.model.entity.QControleCamion;
import com.ayouris.tawassol.common.util.QueryUtils;
import com.ayouris.tawassol.repository.custom.ControleCamionRepositoryCustom;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;

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
