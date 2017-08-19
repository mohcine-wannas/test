package com.ayouris.tawassol.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.AffectationBean;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.Affectation;
import com.ayouris.tawassol.common.model.entity.QAffectation;
import com.ayouris.tawassol.common.util.QueryUtils;
import com.ayouris.tawassol.repository.custom.AffectationRepositoryCustom;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;

/**
 * 
 * @author m.wannas
 *
 */
public class AffectationRepositoryImpl extends ListRepositoryImpl<Affectation,QAffectation>  implements AffectationRepositoryCustom, GridRepositoryCustom<AffectationBean> {

	  @Autowired
	    private CustomModelMapper mapper;
	    private final JPAQueryFactory queryFactory;

	    @Autowired
	    public AffectationRepositoryImpl(JPAQueryFactory queryFactory) {
	        this.queryFactory = queryFactory;
	    }

	    @Override
	    public GridListBean<AffectationBean> findEntities(PageDataBean pageDataBean, Map<String, ColumnDef> columnDefMap){
	        QAffectation affectations = QAffectation.affectation;

	        JPAQuery<Affectation> query = queryFactory.selectFrom(affectations);

	        // Sorting
	        applySort(query, pageDataBean, affectations, columnDefMap);

	        // Filtering
	        applyFilterAndQuickSearch(query, pageDataBean, affectations, columnDefMap);

	        // Count
	        Long totalCount = Long.valueOf(query.fetchCount());

	        // Pagination
	        QueryUtils.applyPagination(query, pageDataBean.getPageNumber(), pageDataBean.getPageSize());

	        // Result
	        List<Affectation> affectationList = query.fetch();

	        GridListBean<AffectationBean> result = new GridListBean<AffectationBean>();

	        result.setTotalItems(totalCount);

	        List<AffectationBean> banqueBeans = mapper.map(affectationList, AffectationBean.LIST_BEAN_TYPE);
	        result.getData().addAll(banqueBeans);

	        return result;
	    }

}
