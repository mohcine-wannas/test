package com.ayouris.tawassol.repository.impl;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.bean.ProfilBean;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.Profil;
import com.ayouris.tawassol.common.model.entity.QProfil;
import com.ayouris.tawassol.common.util.QueryUtils;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by Issmahane EL BAZ on 18/07/2017.
 */
public class ProfilRepositoryImpl extends ListRepositoryImpl<Profil,QProfil> implements GridRepositoryCustom<ProfilBean> {

    @Autowired
    private CustomModelMapper mapper;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public ProfilRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }


    @Override
    public GridListBean<ProfilBean> findEntities(PageDataBean paginateData, Map<String, ColumnDef> columnDefMap) {
        QProfil profils = QProfil.profil;

        JPAQuery<Profil> query =  queryFactory.selectFrom(profils);


        // Sorting
        applySort(query, paginateData, profils, columnDefMap);

        // filtering

        applyFilterAndQuickSearch(query, paginateData, profils, columnDefMap);

        // count
        Long totalCount = Long.valueOf(query.fetchCount());

        // pagination
        QueryUtils.applyPagination(query, paginateData.getPageNumber(), paginateData.getPageSize());


        // Result
        List<Profil> profilList = query.fetch();

        GridListBean<ProfilBean> result = new GridListBean<ProfilBean>();

        result.setTotalItems(totalCount);
        List<ProfilBean>profilBeans = mapper.map(profilList, ProfilBean.LIST_BEAN_TYPE);
        result.getData().addAll(profilBeans);

        return result;
    }
}
