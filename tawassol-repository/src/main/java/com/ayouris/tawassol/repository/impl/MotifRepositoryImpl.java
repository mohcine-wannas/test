package com.ayouris.tawassol.repository.impl;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ayouris.tawassol.admin.model.beans.SiteBean;
import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.MotifBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.entity.*;
import com.ayouris.tawassol.common.util.QueryUtils;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;
import com.ayouris.tawassol.repository.custom.MotifRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;


/**
 * Created by Issmahane EL BAZ on 22/06/2017.
 */
public class MotifRepositoryImpl extends ListRepositoryImpl<Motif,QMotif> implements MotifRepositoryCustom , GridRepositoryCustom<MotifBean> {

    @Autowired
    private CustomModelMapper mapper;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public MotifRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public GridListBean<MotifBean> findEntities(PageDataBean paginateData, Map<String, ColumnDef> columnDefMap) {

        QMotif motifs = QMotif.motif;

        JPAQuery<Motif> query =  queryFactory.selectFrom(motifs);


        // Sorting
        applySort(query, paginateData, motifs, columnDefMap);

        // filtering

        applyFilterAndQuickSearch(query, paginateData, motifs, columnDefMap);

        // count
        Long totalCount = Long.valueOf(query.fetchCount());

        // pagination
        QueryUtils.applyPagination(query, paginateData.getPageNumber(), paginateData.getPageSize());


        // Result
        List<Motif> motifList = query.fetch();

        GridListBean<MotifBean> result = new GridListBean<MotifBean>();

        result.setTotalItems(totalCount);
        List<MotifBean>motifBeans = mapper.map(motifList, MotifBean.LIST_BEAN_TYPE);
        result.getData().addAll(motifBeans);

        return result;
    }


}
