package com.ayouris.tawassol.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.OrdreLivraisonBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.OrdreLivraison;
import com.ayouris.tawassol.common.model.entity.QOrdreLivraison;
import com.ayouris.tawassol.common.util.QueryUtils;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;
import com.ayouris.tawassol.repository.custom.OrdreLivraisonRepositoryCustom;


public class OrdreLivraisonRepositoryImpl extends ListRepositoryImpl<OrdreLivraison, QOrdreLivraison>
		implements OrdreLivraisonRepositoryCustom, GridRepositoryCustom<OrdreLivraisonBean> {

	@Autowired
	private CustomModelMapper mapper;
	private final JPAQueryFactory queryFactory;

	@Autowired
	public OrdreLivraisonRepositoryImpl(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

	@Override
	public GridListBean<OrdreLivraisonBean> findEntities(PageDataBean pageDataBean, Map<String, ColumnDef> columnDefMap) {
		QOrdreLivraison ordreLivraisons = QOrdreLivraison.ordreLivraison;

		JPAQuery<OrdreLivraison> query = queryFactory.selectFrom(ordreLivraisons);

		// Sorting
		applySort(query, pageDataBean, ordreLivraisons, columnDefMap);

		// Filtering
		applyFilterAndQuickSearch(query, pageDataBean, ordreLivraisons, columnDefMap);

		// Count
		Long totalCount = Long.valueOf(query.fetchCount());

		// Pagination
		QueryUtils.applyPagination(query, pageDataBean.getPageNumber(), pageDataBean.getPageSize());

		// Result
		List<OrdreLivraison> ordreLivraisonList = query.fetch();

		GridListBean<OrdreLivraisonBean> result = new GridListBean<OrdreLivraisonBean>();

		result.setTotalItems(totalCount);

		List<OrdreLivraisonBean> ordreLivraisonBeans = mapper.map(ordreLivraisonList, OrdreLivraisonBean.LIST_BEAN_TYPE);
		result.getData().addAll(ordreLivraisonBeans);

		return result;
	}

}
