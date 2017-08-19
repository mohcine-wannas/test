package com.ayouris.tawassol.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.EmplissageBean;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.Emplissage;
import com.ayouris.tawassol.common.model.entity.QEmplissage;
import com.ayouris.tawassol.common.util.QueryUtils;
import com.ayouris.tawassol.repository.custom.EmplissageRepositoryCustom;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;


public class EmplissageRepositoryImpl extends ListRepositoryImpl<Emplissage, QEmplissage>
		implements EmplissageRepositoryCustom, GridRepositoryCustom<EmplissageBean> {

	@Autowired
	private CustomModelMapper mapper;
	private final JPAQueryFactory queryFactory;

	@Autowired
	public EmplissageRepositoryImpl(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

	@Override
	public GridListBean<EmplissageBean> findEntities(PageDataBean pageDataBean, Map<String, ColumnDef> columnDefMap) {
		QEmplissage emplissages = QEmplissage.emplissage;

		JPAQuery<Emplissage> query = queryFactory.selectFrom(emplissages);

		// Sorting
		applySort(query, pageDataBean, emplissages, columnDefMap);

		// Filtering
		applyFilterAndQuickSearch(query, pageDataBean, emplissages, columnDefMap);

		// Count
		Long totalCount = Long.valueOf(query.fetchCount());

		// Pagination
		QueryUtils.applyPagination(query, pageDataBean.getPageNumber(), pageDataBean.getPageSize());

		// Result
		List<Emplissage> emplissageList = query.fetch();

		GridListBean<EmplissageBean> result = new GridListBean<EmplissageBean>();

		result.setTotalItems(totalCount);

		List<EmplissageBean> emplissageBeans = mapper.map(emplissageList, EmplissageBean.LIST_BEAN_TYPE);
		result.getData().addAll(emplissageBeans);

		return result;
	}

}
