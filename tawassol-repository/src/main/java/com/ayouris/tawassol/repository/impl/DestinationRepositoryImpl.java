package com.ayouris.tawassol.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.DestinationBean;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.DestinationOL;
import com.ayouris.tawassol.common.model.entity.QDestinationOL;
import com.ayouris.tawassol.common.util.QueryUtils;
import com.ayouris.tawassol.repository.custom.DestinationRepositoryCustom;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;

/**
 * 
 * @author m.wannas
 *
 */
public class DestinationRepositoryImpl extends ListRepositoryImpl<DestinationOL, QDestinationOL>
		implements DestinationRepositoryCustom, GridRepositoryCustom<DestinationBean> {

	@Autowired
	private CustomModelMapper mapper;
	private final JPAQueryFactory queryFactory;

	@Autowired
	public DestinationRepositoryImpl(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

	@Override
	public GridListBean<DestinationBean> findEntities(PageDataBean pageDataBean, Map<String, ColumnDef> columnDefMap) {
		QDestinationOL destinations = QDestinationOL.destinationOL;

		JPAQuery<DestinationOL> query = queryFactory.selectFrom(destinations);

		// Sorting
		applySort(query, pageDataBean, destinations, columnDefMap);

		// Filtering
		applyFilterAndQuickSearch(query, pageDataBean, destinations, columnDefMap);

		// Count
		Long totalCount = Long.valueOf(query.fetchCount());

		// Pagination
		QueryUtils.applyPagination(query, pageDataBean.getPageNumber(), pageDataBean.getPageSize());

		// Result
		List<DestinationOL> destinationList = query.fetch();

		GridListBean<DestinationBean> result = new GridListBean<DestinationBean>();

		result.setTotalItems(totalCount);

		List<DestinationBean> destBeans = mapper.map(destinationList, DestinationBean.LIST_BEAN_TYPE);
		result.getData().addAll(destBeans);

		return result;
	}

}
