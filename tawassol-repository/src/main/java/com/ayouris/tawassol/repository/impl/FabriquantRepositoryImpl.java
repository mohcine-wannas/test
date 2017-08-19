package com.ayouris.tawassol.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.FabriquantBean;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.Fabriquant;
import com.ayouris.tawassol.common.model.entity.QFabriquant;
import com.ayouris.tawassol.common.util.QueryUtils;
import com.ayouris.tawassol.repository.custom.FabriquantRepositoryCustom;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;

/**
 * 
 * @author m.wannas
 *
 */
public class FabriquantRepositoryImpl extends ListRepositoryImpl<Fabriquant,QFabriquant>  implements FabriquantRepositoryCustom, GridRepositoryCustom<FabriquantBean> {

	  @Autowired
	    private CustomModelMapper mapper;
	    private final JPAQueryFactory queryFactory;

	    @Autowired
	    public FabriquantRepositoryImpl(JPAQueryFactory queryFactory) {
	        this.queryFactory = queryFactory;
	    }

	    @Override
	    public GridListBean<FabriquantBean> findEntities(PageDataBean pageDataBean, Map<String, ColumnDef> columnDefMap){
	        QFabriquant fabriquants = QFabriquant.fabriquant;

	        JPAQuery<Fabriquant> query = queryFactory.selectFrom(fabriquants);

	        // Sorting
	        applySort(query, pageDataBean, fabriquants, columnDefMap);

	        // Filtering
	        applyFilterAndQuickSearch(query, pageDataBean, fabriquants, columnDefMap);

	        // Count
	        Long totalCount = Long.valueOf(query.fetchCount());

	        // Pagination
	        QueryUtils.applyPagination(query, pageDataBean.getPageNumber(), pageDataBean.getPageSize());

	        // Result
	        List<Fabriquant> fabriquantList = query.fetch();

	        GridListBean<FabriquantBean> result = new GridListBean<FabriquantBean>();

	        result.setTotalItems(totalCount);

	        List<FabriquantBean> banqueBeans = mapper.map(fabriquantList, FabriquantBean.LIST_BEAN_TYPE);
	        result.getData().addAll(banqueBeans);

	        return result;
	    }

}
