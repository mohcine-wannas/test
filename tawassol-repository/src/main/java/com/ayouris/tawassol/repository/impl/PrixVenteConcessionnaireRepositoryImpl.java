package com.ayouris.tawassol.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.bean.PrixVenteConcessionnaireBean;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.PrixVenteConcessionnaire;
import com.ayouris.tawassol.common.model.entity.QPrixVenteConcessionnaire;
import com.ayouris.tawassol.common.util.QueryUtils;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;
import com.ayouris.tawassol.repository.custom.PrixVenteConcessionnaireRepositoryCustom;


public class PrixVenteConcessionnaireRepositoryImpl extends ListRepositoryImpl<PrixVenteConcessionnaire, QPrixVenteConcessionnaire>
		implements PrixVenteConcessionnaireRepositoryCustom, GridRepositoryCustom<PrixVenteConcessionnaireBean> {

	@Autowired
	private CustomModelMapper mapper;
	private final JPAQueryFactory queryFactory;

	@Autowired
	public PrixVenteConcessionnaireRepositoryImpl(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

	@Override
	public GridListBean<PrixVenteConcessionnaireBean> findEntities(PageDataBean pageDataBean, Map<String, ColumnDef> columnDefMap) {
		QPrixVenteConcessionnaire prixVenteConcessionnaires = QPrixVenteConcessionnaire.prixVenteConcessionnaire;

		JPAQuery<PrixVenteConcessionnaire> query = queryFactory.selectFrom(prixVenteConcessionnaires);

		// Sorting
		applySort(query, pageDataBean, prixVenteConcessionnaires, columnDefMap);

		// Filtering
		applyFilterAndQuickSearch(query, pageDataBean, prixVenteConcessionnaires, columnDefMap);

		// Count
		Long totalCount = Long.valueOf(query.fetchCount());

		// Pagination
		QueryUtils.applyPagination(query, pageDataBean.getPageNumber(), pageDataBean.getPageSize());

		// Result
		List<PrixVenteConcessionnaire> prixVenteConcessionnaireList = query.fetch();

		GridListBean<PrixVenteConcessionnaireBean> result = new GridListBean<PrixVenteConcessionnaireBean>();

		result.setTotalItems(totalCount);

		List<PrixVenteConcessionnaireBean> prixVenteConcessionnaireBeans = mapper.map(prixVenteConcessionnaireList, PrixVenteConcessionnaireBean.LIST_BEAN_TYPE);
		result.getData().addAll(prixVenteConcessionnaireBeans);

		return result;
	}

}
