package com.ayouris.tawassol.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.PrestationBean;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.Prestation;
import com.ayouris.tawassol.common.model.entity.QPrestation;
import com.ayouris.tawassol.common.util.QueryUtils;
import com.ayouris.tawassol.repository.custom.PrestationRepositoryCustom;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;

/**
 * 
 * @author m.wannas
 *
 */
public class PrestationRepositoryImpl extends ListRepositoryImpl<Prestation,QPrestation>  implements PrestationRepositoryCustom, GridRepositoryCustom<PrestationBean> {

	  @Autowired
	    private CustomModelMapper mapper;
	    private final JPAQueryFactory queryFactory;

	    @Autowired
	    public PrestationRepositoryImpl(JPAQueryFactory queryFactory) {
	        this.queryFactory = queryFactory;
	    }

	    @Override
	    public GridListBean<PrestationBean> findEntities(PageDataBean pageDataBean, Map<String, ColumnDef> columnDefMap){
	        QPrestation prestations = QPrestation.prestation;

	        JPAQuery<Prestation> query = queryFactory.selectFrom(prestations);

	        // Sorting
	        applySort(query, pageDataBean, prestations, columnDefMap);

	        // Filtering
	        applyFilterAndQuickSearch(query, pageDataBean, prestations, columnDefMap);

	        // Count
	        Long totalCount = Long.valueOf(query.fetchCount());

	        // Pagination
	        QueryUtils.applyPagination(query, pageDataBean.getPageNumber(), pageDataBean.getPageSize());

	        // Result
	        List<Prestation> prestationList = query.fetch();

	        GridListBean<PrestationBean> result = new GridListBean<PrestationBean>();

	        result.setTotalItems(totalCount);

	        List<PrestationBean> banqueBeans = mapper.map(prestationList, PrestationBean.LIST_BEAN_TYPE);
	        result.getData().addAll(banqueBeans);

	        return result;
	    }

}
