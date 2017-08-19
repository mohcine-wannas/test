package com.ayouris.tawassol.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.AffectationProduitOperateurBean;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.entity.AffectationProduitOperateur;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.QAffectationProduitOperateur;
import com.ayouris.tawassol.common.util.QueryUtils;
import com.ayouris.tawassol.repository.custom.AffectationProduitOperateurRepositoryCustom;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;

/**
 * 
 * @author m.wannas
 *
 */
public class AffectationProduitOperateurRepositoryImpl extends ListRepositoryImpl<AffectationProduitOperateur,QAffectationProduitOperateur>  implements AffectationProduitOperateurRepositoryCustom, GridRepositoryCustom<AffectationProduitOperateurBean> {

	  @Autowired
	    private CustomModelMapper mapper;
	    private final JPAQueryFactory queryFactory;

	    @Autowired
	    public AffectationProduitOperateurRepositoryImpl(JPAQueryFactory queryFactory) {
	        this.queryFactory = queryFactory;
	    }

	    @Override
	    public GridListBean<AffectationProduitOperateurBean> findEntities(PageDataBean pageDataBean, Map<String, ColumnDef> columnDefMap){
	        QAffectationProduitOperateur affectationProduitOperateurs = QAffectationProduitOperateur.affectationProduitOperateur;

	        JPAQuery<AffectationProduitOperateur> query = queryFactory.selectFrom(affectationProduitOperateurs);

	        // Sorting
	        applySort(query, pageDataBean, affectationProduitOperateurs, columnDefMap);

	        // Filtering
	        applyFilterAndQuickSearch(query, pageDataBean, affectationProduitOperateurs, columnDefMap);

	        // Count
	        Long totalCount = Long.valueOf(query.fetchCount());

	        // Pagination
	        QueryUtils.applyPagination(query, pageDataBean.getPageNumber(), pageDataBean.getPageSize());

	        // Result
	        List<AffectationProduitOperateur> affectationProduitOperateurList = query.fetch();

	        GridListBean<AffectationProduitOperateurBean> result = new GridListBean<AffectationProduitOperateurBean>();

	        result.setTotalItems(totalCount);

	        List<AffectationProduitOperateurBean> banqueBeans = mapper.map(affectationProduitOperateurList, AffectationProduitOperateurBean.LIST_BEAN_TYPE);
	        result.getData().addAll(banqueBeans);

	        return result;
	    }

}
