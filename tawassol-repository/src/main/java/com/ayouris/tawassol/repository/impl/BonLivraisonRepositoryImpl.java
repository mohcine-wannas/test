package com.ayouris.tawassol.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.BonLivraisonBean;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.entity.BonLivraison;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.QBonLivraison;
import com.ayouris.tawassol.common.util.QueryUtils;
import com.ayouris.tawassol.repository.custom.BonLivraisonRepositoryCustom;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;


public class BonLivraisonRepositoryImpl extends ListRepositoryImpl<BonLivraison, QBonLivraison>
		implements BonLivraisonRepositoryCustom, GridRepositoryCustom<BonLivraisonBean> {

	@Autowired
	private CustomModelMapper mapper;
	private final JPAQueryFactory queryFactory;

	@Autowired
	public BonLivraisonRepositoryImpl(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

	@Override
	public GridListBean<BonLivraisonBean> findEntities(PageDataBean pageDataBean, Map<String, ColumnDef> columnDefMap) {
		QBonLivraison bonLivraisons = QBonLivraison.bonLivraison;

		JPAQuery<BonLivraison> query = queryFactory.selectFrom(bonLivraisons);

		// Sorting
		applySort(query, pageDataBean, bonLivraisons, columnDefMap);

		// Filtering
		applyFilterAndQuickSearch(query, pageDataBean, bonLivraisons, columnDefMap);

		// Count
		Long totalCount = Long.valueOf(query.fetchCount());

		// Pagination
		QueryUtils.applyPagination(query, pageDataBean.getPageNumber(), pageDataBean.getPageSize());

		// Result
		List<BonLivraison> bonLivraisonList = query.fetch();

		GridListBean<BonLivraisonBean> result = new GridListBean<BonLivraisonBean>();

		result.setTotalItems(totalCount);

		List<BonLivraisonBean> bonLivraisonBeans = mapper.map(bonLivraisonList, BonLivraisonBean.LIST_BEAN_TYPE);
		result.getData().addAll(bonLivraisonBeans);

		return result;
	}

}
