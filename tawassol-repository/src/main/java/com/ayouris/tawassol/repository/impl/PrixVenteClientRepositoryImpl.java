package com.ayouris.tawassol.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.bean.PrixVenteClientBean;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.PrixVenteClient;
import com.ayouris.tawassol.common.model.entity.QPrixVenteClient;
import com.ayouris.tawassol.common.util.QueryUtils;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;
import com.ayouris.tawassol.repository.custom.PrixVenteClientRepositoryCustom;


public class PrixVenteClientRepositoryImpl extends ListRepositoryImpl<PrixVenteClient, QPrixVenteClient>
		implements PrixVenteClientRepositoryCustom, GridRepositoryCustom<PrixVenteClientBean> {

	@Autowired
	private CustomModelMapper mapper;
	private final JPAQueryFactory queryFactory;

	@Autowired
	public PrixVenteClientRepositoryImpl(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

	@Override
	public GridListBean<PrixVenteClientBean> findEntities(PageDataBean pageDataBean, Map<String, ColumnDef> columnDefMap) {
		QPrixVenteClient prixVenteClients = QPrixVenteClient.prixVenteClient;

		JPAQuery<PrixVenteClient> query = queryFactory.selectFrom(prixVenteClients);

		// Sorting
		applySort(query, pageDataBean, prixVenteClients, columnDefMap);

		// Filtering
		applyFilterAndQuickSearch(query, pageDataBean, prixVenteClients, columnDefMap);

		// Count
		Long totalCount = Long.valueOf(query.fetchCount());

		// Pagination
		QueryUtils.applyPagination(query, pageDataBean.getPageNumber(), pageDataBean.getPageSize());

		// Result
		List<PrixVenteClient> prixVenteClientList = query.fetch();

		GridListBean<PrixVenteClientBean> result = new GridListBean<PrixVenteClientBean>();

		result.setTotalItems(totalCount);

		List<PrixVenteClientBean> prixVenteClientBeans = mapper.map(prixVenteClientList, PrixVenteClientBean.LIST_BEAN_TYPE);
		result.getData().addAll(prixVenteClientBeans);

		return result;
	}

}
