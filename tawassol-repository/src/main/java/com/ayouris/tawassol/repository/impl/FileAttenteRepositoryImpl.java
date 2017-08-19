package com.ayouris.tawassol.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.FileAttenteBean;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.FileAttente;
import com.ayouris.tawassol.common.model.entity.QFileAttente;
import com.ayouris.tawassol.common.util.QueryUtils;
import com.ayouris.tawassol.repository.custom.FileAttenteRepositoryCustom;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;


public class FileAttenteRepositoryImpl extends ListRepositoryImpl<FileAttente, QFileAttente>
		implements FileAttenteRepositoryCustom, GridRepositoryCustom<FileAttenteBean> {

	@Autowired
	private CustomModelMapper mapper;
	private final JPAQueryFactory queryFactory;

	@Autowired
	public FileAttenteRepositoryImpl(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

	@Override
	public GridListBean<FileAttenteBean> findEntities(PageDataBean pageDataBean, Map<String, ColumnDef> columnDefMap) {
		QFileAttente fileAttentes = QFileAttente.fileAttente;

		JPAQuery<FileAttente> query = queryFactory.selectFrom(fileAttentes);

		// Sorting
		applySort(query, pageDataBean, fileAttentes, columnDefMap);

		// Filtering
		applyFilterAndQuickSearch(query, pageDataBean, fileAttentes, columnDefMap);

		// Count
		Long totalCount = Long.valueOf(query.fetchCount());

		// Pagination
		QueryUtils.applyPagination(query, pageDataBean.getPageNumber(), pageDataBean.getPageSize());

		// Result
		List<FileAttente> fileAttentList = query.fetch();

		GridListBean<FileAttenteBean> result = new GridListBean<FileAttenteBean>();

		result.setTotalItems(totalCount);

		List<FileAttenteBean> fileAttentBeans = mapper.map(fileAttentList, FileAttenteBean.LIST_BEAN_TYPE);
		result.getData().addAll(fileAttentBeans);

		return result;
	}

}
