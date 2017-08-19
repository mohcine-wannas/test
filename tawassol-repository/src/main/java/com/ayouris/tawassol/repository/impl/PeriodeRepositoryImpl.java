package com.ayouris.tawassol.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.PeriodeBean;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.Periode;
import com.ayouris.tawassol.common.model.entity.QPeriode;
import com.ayouris.tawassol.common.util.QueryUtils;
import com.ayouris.tawassol.repository.custom.PeriodeRepositoryCustom;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;

/**
 * 
 * @author m.wannas
 *
 */
public class PeriodeRepositoryImpl extends ListRepositoryImpl<Periode,QPeriode>  implements PeriodeRepositoryCustom, GridRepositoryCustom<PeriodeBean> {

	  @Autowired
	    private CustomModelMapper mapper;
	    private final JPAQueryFactory queryFactory;

	    @Autowired
	    public PeriodeRepositoryImpl(JPAQueryFactory queryFactory) {
	        this.queryFactory = queryFactory;
	    }

	    @Override
	    public GridListBean<PeriodeBean> findEntities(PageDataBean pageDataBean, Map<String, ColumnDef> columnDefMap){
	        QPeriode periodes = QPeriode.periode;

	        JPAQuery<Periode> query = queryFactory.selectFrom(periodes);

	        // Sorting
	        applySort(query, pageDataBean, periodes, columnDefMap);

	        // Filtering
	        applyFilterAndQuickSearch(query, pageDataBean, periodes, columnDefMap);

	        // Count
	        Long totalCount = Long.valueOf(query.fetchCount());

	        // Pagination
	        QueryUtils.applyPagination(query, pageDataBean.getPageNumber(), pageDataBean.getPageSize());

	        // Result
	        List<Periode> periodeList = query.fetch();

	        GridListBean<PeriodeBean> result = new GridListBean<PeriodeBean>();

	        result.setTotalItems(totalCount);

	        List<PeriodeBean> banqueBeans = mapper.map(periodeList, PeriodeBean.LIST_BEAN_TYPE);
	        result.getData().addAll(banqueBeans);

	        return result;
	    }

}
