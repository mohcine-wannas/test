package com.ayouris.tawassol.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.ProduitBean;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.Produit;
import com.ayouris.tawassol.common.model.entity.QProduit;
import com.ayouris.tawassol.common.util.QueryUtils;
import com.ayouris.tawassol.repository.custom.ProduitRepositoryCustom;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;

/**
 * 
 * @author m.wannas
 *
 */
public class ProduitRepositoryImpl extends ListRepositoryImpl<Produit,QProduit>  implements ProduitRepositoryCustom, GridRepositoryCustom<ProduitBean> {

	  @Autowired
	    private CustomModelMapper mapper;
	    private final JPAQueryFactory queryFactory;

	    @Autowired
	    public ProduitRepositoryImpl(JPAQueryFactory queryFactory) {
	        this.queryFactory = queryFactory;
	    }

	    @Override
	    public GridListBean<ProduitBean> findEntities(PageDataBean pageDataBean, Map<String, ColumnDef> columnDefMap){
	        QProduit produits = QProduit.produit;

	        JPAQuery<Produit> query = queryFactory.selectFrom(produits);

	        // Sorting
	        applySort(query, pageDataBean, produits, columnDefMap);

	        // Filtering
	        applyFilterAndQuickSearch(query, pageDataBean, produits, columnDefMap);

	        // Count
	        Long totalCount = Long.valueOf(query.fetchCount());

	        // Pagination
	        QueryUtils.applyPagination(query, pageDataBean.getPageNumber(), pageDataBean.getPageSize());

	        // Result
	        List<Produit> produitList = query.fetch();

	        GridListBean<ProduitBean> result = new GridListBean<ProduitBean>();

	        result.setTotalItems(totalCount);

	        List<ProduitBean> banqueBeans = mapper.map(produitList, ProduitBean.LIST_BEAN_TYPE);
	        result.getData().addAll(banqueBeans);

	        return result;
	    }

}
