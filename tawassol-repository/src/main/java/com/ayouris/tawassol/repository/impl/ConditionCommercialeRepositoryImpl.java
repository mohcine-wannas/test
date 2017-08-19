package com.ayouris.tawassol.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.ConditionCommercialeBean;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.ConditionCommerciale;
import com.ayouris.tawassol.common.model.entity.QConditionCommerciale;
import com.ayouris.tawassol.common.util.QueryUtils;
import com.ayouris.tawassol.repository.custom.ConditionCommercialeRepositoryCustom;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;

/**
 * 
 * @author m.wannas
 *
 */
public class ConditionCommercialeRepositoryImpl extends ListRepositoryImpl<ConditionCommerciale,QConditionCommerciale>  implements ConditionCommercialeRepositoryCustom, GridRepositoryCustom<ConditionCommercialeBean> {

	  @Autowired
	    private CustomModelMapper mapper;
	    private final JPAQueryFactory queryFactory;

	    @Autowired
	    public ConditionCommercialeRepositoryImpl(JPAQueryFactory queryFactory) {
	        this.queryFactory = queryFactory;
	    }

	    @Override
	    public GridListBean<ConditionCommercialeBean> findEntities(PageDataBean pageDataBean, Map<String, ColumnDef> columnDefMap){
	        QConditionCommerciale conditionCommerciales = QConditionCommerciale.conditionCommerciale;

	        JPAQuery<ConditionCommerciale> query = queryFactory.selectFrom(conditionCommerciales);

	        // Sorting
	        applySort(query, pageDataBean, conditionCommerciales, columnDefMap);

	        // Filtering
	        applyFilterAndQuickSearch(query, pageDataBean, conditionCommerciales, columnDefMap);

	        // Count
	        Long totalCount = Long.valueOf(query.fetchCount());

	        // Pagination
	        QueryUtils.applyPagination(query, pageDataBean.getPageNumber(), pageDataBean.getPageSize());

	        // Result
	        List<ConditionCommerciale> conditionCommercialeList = query.fetch();

	        GridListBean<ConditionCommercialeBean> result = new GridListBean<ConditionCommercialeBean>();

	        result.setTotalItems(totalCount);

	        List<ConditionCommercialeBean> banqueBeans = mapper.map(conditionCommercialeList, ConditionCommercialeBean.LIST_BEAN_TYPE);
	        result.getData().addAll(banqueBeans);

	        return result;
	    }

}
