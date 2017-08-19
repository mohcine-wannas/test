package com.ayouris.tawassol.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.CiterneBean;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.Citerne;
import com.ayouris.tawassol.common.model.entity.QCiterne;
import com.ayouris.tawassol.common.util.QueryUtils;
import com.ayouris.tawassol.repository.custom.CiterneRepositoryCustom;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;

/**
 * 
 * @author m.wannas
 *
 */
public class CiterneRepositoryImpl extends ListRepositoryImpl<Citerne,QCiterne>  implements CiterneRepositoryCustom, GridRepositoryCustom<CiterneBean> {

	  @Autowired
	    private CustomModelMapper mapper;
	    private final JPAQueryFactory queryFactory;

	    @Autowired
	    public CiterneRepositoryImpl(JPAQueryFactory queryFactory) {
	        this.queryFactory = queryFactory;
	    }

	    @Override
	    public GridListBean<CiterneBean> findEntities(PageDataBean pageDataBean, Map<String, ColumnDef> columnDefMap){
	        QCiterne citernes = QCiterne.citerne;

	        JPAQuery<Citerne> query = queryFactory.selectFrom(citernes);

	        // Sorting
	        applySort(query, pageDataBean, citernes, columnDefMap);

	        // Filtering
	        applyFilterAndQuickSearch(query, pageDataBean, citernes, columnDefMap);

	        // Count
	        Long totalCount = Long.valueOf(query.fetchCount());

	        // Pagination
	        QueryUtils.applyPagination(query, pageDataBean.getPageNumber(), pageDataBean.getPageSize());

	        // Result
	        List<Citerne> citerneList = query.fetch();

	        GridListBean<CiterneBean> result = new GridListBean<CiterneBean>();

	        result.setTotalItems(totalCount);

	        List<CiterneBean> banqueBeans = mapper.map(citerneList, CiterneBean.LIST_BEAN_TYPE);
	        result.getData().addAll(banqueBeans);

	        return result;
	    }

}
