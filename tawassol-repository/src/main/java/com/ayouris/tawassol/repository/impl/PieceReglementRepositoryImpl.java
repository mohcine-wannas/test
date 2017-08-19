package com.ayouris.tawassol.repository.impl;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.bean.PieceReglementBean;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.PieceReglement;
import com.ayouris.tawassol.common.model.entity.QPieceReglement;
import com.ayouris.tawassol.common.util.QueryUtils;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;
import com.ayouris.tawassol.repository.custom.PieceReglementRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by Issmahane EL BAZ on 20/07/2017.
 */
public class PieceReglementRepositoryImpl extends ListRepositoryImpl<PieceReglement,QPieceReglement>
        implements PieceReglementRepositoryCustom, GridRepositoryCustom<PieceReglementBean> {


    @Autowired
    private CustomModelMapper mapper;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public PieceReglementRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public GridListBean<PieceReglementBean> findEntities(PageDataBean pageDataBean, Map<String, ColumnDef> columnDefMap) {

        QPieceReglement pieceReglements = QPieceReglement.pieceReglement;

        JPAQuery<PieceReglement> query = queryFactory.selectFrom(pieceReglements);

        // Sorting
        applySort(query, pageDataBean, pieceReglements, columnDefMap);

        // Filtering
        applyFilterAndQuickSearch(query, pageDataBean, pieceReglements, columnDefMap);

        // Count
        Long totalCount = Long.valueOf(query.fetchCount());

        // Pagination
        QueryUtils.applyPagination(query, pageDataBean.getPageNumber(), pageDataBean.getPageSize());

        // Result
        List<PieceReglement> pieceReglementList = query.fetch();

        GridListBean<PieceReglementBean> result = new GridListBean<PieceReglementBean>();

        result.setTotalItems(totalCount);

        List<PieceReglementBean> banqueBeans = mapper.map(pieceReglementList, PieceReglementBean.LIST_BEAN_TYPE);
        result.getData().addAll(banqueBeans);

        return result;
    }
}
