package com.ayouris.tawassol.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.JourneeActiviteBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.JourneeActivite;
import com.ayouris.tawassol.common.model.entity.QJourneeActivite;
import com.ayouris.tawassol.common.util.QueryUtils;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;
import com.ayouris.tawassol.repository.custom.JourneeActiviteRepositoryCustom;


public class JourneeActiviteRepositoryImpl extends ListRepositoryImpl<JourneeActivite, QJourneeActivite>
		implements JourneeActiviteRepositoryCustom, GridRepositoryCustom<JourneeActiviteBean> {

	@Autowired
	private CustomModelMapper mapper;
	private final JPAQueryFactory queryFactory;

	@Autowired
	public JourneeActiviteRepositoryImpl(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

	@Override
	public GridListBean<JourneeActiviteBean> findEntities(PageDataBean pageDataBean, Map<String, ColumnDef> columnDefMap) {
		QJourneeActivite journeeActivites = QJourneeActivite.journeeActivite;

		JPAQuery<JourneeActivite> query = queryFactory.selectFrom(journeeActivites);

		// Sorting
		applySort(query, pageDataBean, journeeActivites, columnDefMap);

		// Filtering
		applyFilterAndQuickSearch(query, pageDataBean, journeeActivites, columnDefMap);

		// Count
		Long totalCount = Long.valueOf(query.fetchCount());

		// Pagination
		QueryUtils.applyPagination(query, pageDataBean.getPageNumber(), pageDataBean.getPageSize());

		// Result
		List<JourneeActivite> journeeActiviteList = query.fetch();

		GridListBean<JourneeActiviteBean> result = new GridListBean<JourneeActiviteBean>();

		result.setTotalItems(totalCount);

		List<JourneeActiviteBean> journeeActiviteBeans = mapper.map(journeeActiviteList, JourneeActiviteBean.LIST_BEAN_TYPE);
		result.getData().addAll(journeeActiviteBeans);

		return result;
	}

}
