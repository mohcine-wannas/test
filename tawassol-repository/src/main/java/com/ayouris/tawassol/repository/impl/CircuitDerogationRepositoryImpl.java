package com.ayouris.tawassol.repository.impl;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.CircuitDerogationBean;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.entity.BonLivraison;
import com.ayouris.tawassol.common.model.entity.CircuitDerogation;
import com.ayouris.tawassol.common.model.entity.ColumnDef;

import com.ayouris.tawassol.common.model.entity.QCircuitDerogation;
import com.ayouris.tawassol.common.util.QueryUtils;
import com.ayouris.tawassol.repository.custom.CircuitDerogationRepositoryCustom;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by Issmahane EL BAZ on 11/07/2017.
 */
public class CircuitDerogationRepositoryImpl extends ListRepositoryImpl<CircuitDerogation, QCircuitDerogation>
        implements CircuitDerogationRepositoryCustom, GridRepositoryCustom<CircuitDerogationBean> {

    @Autowired
    private CustomModelMapper mapper;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public CircuitDerogationRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public GridListBean<CircuitDerogationBean> findEntities(PageDataBean pageDataBean, Map<String, ColumnDef> columnDefMap) {
        QCircuitDerogation circuitDerogations = QCircuitDerogation.circuitDerogation;

        JPAQuery<CircuitDerogation> query = queryFactory.selectFrom(circuitDerogations);

        // Sorting
        applySort(query, pageDataBean, circuitDerogations, columnDefMap);

        // Filtering
        applyFilterAndQuickSearch(query, pageDataBean, circuitDerogations, columnDefMap);

        // Count
        Long totalCount = Long.valueOf(query.fetchCount());

        // Pagination
        QueryUtils.applyPagination(query, pageDataBean.getPageNumber(), pageDataBean.getPageSize());

        // Result
        List<CircuitDerogation> circuitDerogationList = query.fetch();

        GridListBean<CircuitDerogationBean> result = new GridListBean<CircuitDerogationBean>();

        result.setTotalItems(totalCount);

        List<CircuitDerogationBean> circuitDerogationsBeans = mapper.map(circuitDerogationList, CircuitDerogationBean.LIST_BEAN_TYPE);
        result.getData().addAll(circuitDerogationsBeans);

        return result;
    }
}
