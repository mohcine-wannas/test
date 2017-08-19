package com.ayouris.tawassol.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.TransporteurBean;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.Transporteur;
import com.ayouris.tawassol.common.model.entity.QTransporteur;
import com.ayouris.tawassol.common.util.QueryUtils;
import com.ayouris.tawassol.repository.custom.TransporteurRepositoryCustom;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;

/**
 * 
 * @author m.wannas
 *
 */
public class TransporteurRepositoryImpl extends ListRepositoryImpl<Transporteur,QTransporteur>  implements TransporteurRepositoryCustom, GridRepositoryCustom<TransporteurBean> {

	  @Autowired
	    private CustomModelMapper mapper;
	    private final JPAQueryFactory queryFactory;

	    @Autowired
	    public TransporteurRepositoryImpl(JPAQueryFactory queryFactory) {
	        this.queryFactory = queryFactory;
	    }

	    @Override
	    public GridListBean<TransporteurBean> findEntities(PageDataBean pageDataBean, Map<String, ColumnDef> columnDefMap){
	        QTransporteur transporteurs = QTransporteur.transporteur;

	        JPAQuery<Transporteur> query = queryFactory.selectFrom(transporteurs);

	        // Sorting
	        applySort(query, pageDataBean, transporteurs, columnDefMap);

	        // Filtering
	        applyFilterAndQuickSearch(query, pageDataBean, transporteurs, columnDefMap);

	        // Count
	        Long totalCount = Long.valueOf(query.fetchCount());

	        // Pagination
	        QueryUtils.applyPagination(query, pageDataBean.getPageNumber(), pageDataBean.getPageSize());

	        // Result
	        List<Transporteur> transporteurList = query.fetch();

	        GridListBean<TransporteurBean> result = new GridListBean<TransporteurBean>();

	        result.setTotalItems(totalCount);

	        List<TransporteurBean> transporteurBeans = mapper.map(transporteurList, TransporteurBean.LIST_BEAN_TYPE);
	        result.getData().addAll(transporteurBeans);

	        return result;
	    }

}
