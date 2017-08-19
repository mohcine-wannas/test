package com.ayouris.tawassol.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.FournisseurBean;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.Fournisseur;
import com.ayouris.tawassol.common.model.entity.QFournisseur;
import com.ayouris.tawassol.common.util.QueryUtils;
import com.ayouris.tawassol.repository.custom.FournisseurRepositoryCustom;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;

/**
 * 
 * @author m.wannas
 *
 */
public class FournisseurRepositoryImpl extends ListRepositoryImpl<Fournisseur,QFournisseur>  implements FournisseurRepositoryCustom, GridRepositoryCustom<FournisseurBean> {

	  @Autowired
	    private CustomModelMapper mapper;
	    private final JPAQueryFactory queryFactory;

	    @Autowired
	    public FournisseurRepositoryImpl(JPAQueryFactory queryFactory) {
	        this.queryFactory = queryFactory;
	    }

	    @Override
	    public GridListBean<FournisseurBean> findEntities(PageDataBean pageDataBean, Map<String, ColumnDef> columnDefMap){
	        QFournisseur fournisseurs = QFournisseur.fournisseur;

	        JPAQuery<Fournisseur> query = queryFactory.selectFrom(fournisseurs);

	        // Sorting
	        applySort(query, pageDataBean, fournisseurs, columnDefMap);

	        // Filtering
	        applyFilterAndQuickSearch(query, pageDataBean, fournisseurs, columnDefMap);

	        // Count
	        Long totalCount = Long.valueOf(query.fetchCount());

	        // Pagination
	        QueryUtils.applyPagination(query, pageDataBean.getPageNumber(), pageDataBean.getPageSize());

	        // Result
	        List<Fournisseur> fournisseurList = query.fetch();

	        GridListBean<FournisseurBean> result = new GridListBean<FournisseurBean>();

	        result.setTotalItems(totalCount);

	        List<FournisseurBean> banqueBeans = mapper.map(fournisseurList, FournisseurBean.LIST_BEAN_TYPE);
	        result.getData().addAll(banqueBeans);

	        return result;
	    }

}
