package com.ayouris.tawassol.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.MarqueBean;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.Marque;
import com.ayouris.tawassol.common.model.entity.QMarque;
import com.ayouris.tawassol.common.util.QueryUtils;
import com.ayouris.tawassol.repository.custom.MarqueRepositoryCustom;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;

/**
 * 
 * @author m.wannas
 *
 */
public class MarqueRepositoryImpl extends ListRepositoryImpl<Marque,QMarque>  implements MarqueRepositoryCustom, GridRepositoryCustom<MarqueBean> {

	  @Autowired
	    private CustomModelMapper mapper;
	    private final JPAQueryFactory queryFactory;

	    @Autowired
	    public MarqueRepositoryImpl(JPAQueryFactory queryFactory) {
	        this.queryFactory = queryFactory;
	    }

	    @Override
	    public GridListBean<MarqueBean> findEntities(PageDataBean pageDataBean, Map<String, ColumnDef> columnDefMap){
	        QMarque marques = QMarque.marque;

	        JPAQuery<Marque> query = queryFactory.selectFrom(marques);

	        // Sorting
	        applySort(query, pageDataBean, marques, columnDefMap);

	        // Filtering
	        applyFilterAndQuickSearch(query, pageDataBean, marques, columnDefMap);

	        // Count
	        Long totalCount = Long.valueOf(query.fetchCount());

	        // Pagination
	        QueryUtils.applyPagination(query, pageDataBean.getPageNumber(), pageDataBean.getPageSize());

	        // Result
	        List<Marque> marqueList = query.fetch();

	        GridListBean<MarqueBean> result = new GridListBean<MarqueBean>();

	        result.setTotalItems(totalCount);

	        List<MarqueBean> banqueBeans = mapper.map(marqueList, MarqueBean.LIST_BEAN_TYPE);
	        result.getData().addAll(banqueBeans);

	        return result;
	    }

}
