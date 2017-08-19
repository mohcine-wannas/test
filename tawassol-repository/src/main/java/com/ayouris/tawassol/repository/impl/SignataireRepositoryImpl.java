package com.ayouris.tawassol.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.SignataireBean;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.Signataire;
import com.ayouris.tawassol.common.model.entity.QSignataire;
import com.ayouris.tawassol.common.util.QueryUtils;
import com.ayouris.tawassol.repository.custom.SignataireRepositoryCustom;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;

/**
 * 
 * @author m.wannas
 *
 */
public class SignataireRepositoryImpl extends ListRepositoryImpl<Signataire,QSignataire>  implements SignataireRepositoryCustom, GridRepositoryCustom<SignataireBean> {

	  @Autowired
	    private CustomModelMapper mapper;
	    private final JPAQueryFactory queryFactory;

	    @Autowired
	    public SignataireRepositoryImpl(JPAQueryFactory queryFactory) {
	        this.queryFactory = queryFactory;
	    }

	    @Override
	    public GridListBean<SignataireBean> findEntities(PageDataBean pageDataBean, Map<String, ColumnDef> columnDefMap){
	        QSignataire signataires = QSignataire.signataire;

	        JPAQuery<Signataire> query = queryFactory.selectFrom(signataires);

	        // Sorting
	        applySort(query, pageDataBean, signataires, columnDefMap);

	        // Filtering
	        applyFilterAndQuickSearch(query, pageDataBean, signataires, columnDefMap);

	        // Count
	        Long totalCount = Long.valueOf(query.fetchCount());

	        // Pagination
	        QueryUtils.applyPagination(query, pageDataBean.getPageNumber(), pageDataBean.getPageSize());

	        // Result
	        List<Signataire> signataireList = query.fetch();

	        GridListBean<SignataireBean> result = new GridListBean<SignataireBean>();

	        result.setTotalItems(totalCount);

	        List<SignataireBean> banqueBeans = mapper.map(signataireList, SignataireBean.LIST_BEAN_TYPE);
	        result.getData().addAll(banqueBeans);

	        return result;
	    }

}
