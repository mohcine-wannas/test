package com.ayouris.tawassol.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.FicheMarcheBean;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.FicheMarche;
import com.ayouris.tawassol.common.model.entity.QFicheMarche;
import com.ayouris.tawassol.common.util.QueryUtils;
import com.ayouris.tawassol.repository.custom.FicheMarcheRepositoryCustom;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;


public class FicheMarcheRepositoryImpl extends ListRepositoryImpl<FicheMarche, QFicheMarche>
		implements FicheMarcheRepositoryCustom, GridRepositoryCustom<FicheMarcheBean> {

	@Autowired
	private CustomModelMapper mapper;
	private final JPAQueryFactory queryFactory;

	@Autowired
	public FicheMarcheRepositoryImpl(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

	@Override
	public GridListBean<FicheMarcheBean> findEntities(PageDataBean pageDataBean, Map<String, ColumnDef> columnDefMap) {
		QFicheMarche ficheMarches = QFicheMarche.ficheMarche;

		JPAQuery<FicheMarche> query = queryFactory.selectFrom(ficheMarches);

		// Sorting
		applySort(query, pageDataBean, ficheMarches, columnDefMap);

		// Filtering
		applyFilterAndQuickSearch(query, pageDataBean, ficheMarches, columnDefMap);

		// Count
		Long totalCount = Long.valueOf(query.fetchCount());

		// Pagination
		QueryUtils.applyPagination(query, pageDataBean.getPageNumber(), pageDataBean.getPageSize());

		// Result
		List<FicheMarche> ficheMarcheList = query.fetch();

		GridListBean<FicheMarcheBean> result = new GridListBean<FicheMarcheBean>();

		result.setTotalItems(totalCount);

		List<FicheMarcheBean> ficheMarcheBeans = mapper.map(ficheMarcheList, FicheMarcheBean.LIST_BEAN_TYPE);
		result.getData().addAll(ficheMarcheBeans);

		return result;
	}

}
