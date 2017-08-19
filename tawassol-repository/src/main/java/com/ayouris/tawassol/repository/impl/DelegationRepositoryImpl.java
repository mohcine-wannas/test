package com.ayouris.tawassol.repository.impl;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.DelegationBean;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.entity.Delegation;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.QDelegation;
import com.ayouris.tawassol.common.util.QueryUtils;
import com.ayouris.tawassol.repository.custom.DelegationRepositoryCustom;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by Issmahane EL BAZ on 14/07/2017.
 */
public class DelegationRepositoryImpl extends ListRepositoryImpl<Delegation, QDelegation>
        implements DelegationRepositoryCustom, GridRepositoryCustom<DelegationBean> {

    @Autowired
    private CustomModelMapper mapper;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public DelegationRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public GridListBean<DelegationBean> findEntities(PageDataBean pageDataBean, Map<String, ColumnDef> columnDefMap) {
        QDelegation delegations = QDelegation.delegation;

        JPAQuery<Delegation> query = queryFactory.selectFrom(delegations);

        // Sorting
        applySort(query, pageDataBean, delegations, columnDefMap);

        // Filtering
        applyFilterAndQuickSearch(query, pageDataBean, delegations, columnDefMap);

        // Count
        Long totalCount = Long.valueOf(query.fetchCount());

        // Pagination
        QueryUtils.applyPagination(query, pageDataBean.getPageNumber(), pageDataBean.getPageSize());

        // Result
        List<Delegation> delegationList = query.fetch();

        GridListBean<DelegationBean> result = new GridListBean<DelegationBean>();

        result.setTotalItems(totalCount);

        List<DelegationBean> delegationsBeans = mapper.map(delegationList, DelegationBean.LIST_BEAN_TYPE);
        result.getData().addAll(delegationsBeans);

        return result;
    }
}

