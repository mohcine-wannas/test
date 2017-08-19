package com.ayouris.tawassol.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.ClientBean;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.entity.Client;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.QClient;
import com.ayouris.tawassol.common.util.QueryUtils;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;

/**
 * 
 * @author m.wannas
 *
 */
public class ClientRepositoryImpl extends ListRepositoryImpl<Client,QClient> implements GridRepositoryCustom<ClientBean>{

    @Autowired
    private CustomModelMapper mapper;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public ClientRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public GridListBean<ClientBean> findEntities(PageDataBean pageDataBean, Map<String, ColumnDef> columnDefMap){
        QClient clients = QClient.client;

        JPAQuery<Client> query = queryFactory.selectFrom(clients);

        // Sorting
        applySort(query, pageDataBean, clients, columnDefMap);

        // Filtering
        applyFilterAndQuickSearch(query, pageDataBean, clients, columnDefMap);

        // Count
        Long totalCount = Long.valueOf(query.fetchCount());

        // Pagination
        QueryUtils.applyPagination(query, pageDataBean.getPageNumber(), pageDataBean.getPageSize());

        // Result
        List<Client> clientList = query.fetch();

        GridListBean<ClientBean> result = new GridListBean<ClientBean>();

        result.setTotalItems(totalCount);

        List<ClientBean> banqueBeans = mapper.map(clientList, ClientBean.LIST_BEAN_TYPE);
        result.getData().addAll(banqueBeans);

        return result;
    }

}
