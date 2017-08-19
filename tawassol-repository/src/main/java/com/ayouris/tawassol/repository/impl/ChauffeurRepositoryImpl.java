package com.ayouris.tawassol.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.ChauffeurBean;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.entity.Chauffeur;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.QChauffeur;
import com.ayouris.tawassol.common.util.QueryUtils;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;

/**
 * 
 * @author m.wannas
 *
 */
public class ChauffeurRepositoryImpl extends ListRepositoryImpl<Chauffeur,QChauffeur> implements GridRepositoryCustom<ChauffeurBean>{

    @Autowired
    private CustomModelMapper mapper;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public ChauffeurRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public GridListBean<ChauffeurBean> findEntities(PageDataBean pageDataBean, Map<String, ColumnDef> columnDefMap){
        QChauffeur chauffeurs = QChauffeur.chauffeur;

        JPAQuery<Chauffeur> query = queryFactory.selectFrom(chauffeurs);

        // Sorting
        applySort(query, pageDataBean, chauffeurs, columnDefMap);

        // Filtering
        applyFilterAndQuickSearch(query, pageDataBean, chauffeurs, columnDefMap);

        // Count
        Long totalCount = Long.valueOf(query.fetchCount());

        // Pagination
        QueryUtils.applyPagination(query, pageDataBean.getPageNumber(), pageDataBean.getPageSize());

        // Result
        List<Chauffeur> chauffeurList = query.fetch();

        GridListBean<ChauffeurBean> result = new GridListBean<ChauffeurBean>();

        result.setTotalItems(totalCount);

        List<ChauffeurBean> banqueBeans = mapper.map(chauffeurList, ChauffeurBean.LIST_BEAN_TYPE);
        result.getData().addAll(banqueBeans);

        return result;
    }

}
