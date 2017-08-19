package com.ayouris.tawassol.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.TypePieceReglementBean;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.TypePieceReglement;
import com.ayouris.tawassol.common.model.entity.QTypePieceReglement;
import com.ayouris.tawassol.common.util.QueryUtils;
import com.ayouris.tawassol.repository.custom.TypePieceReglementRepositoryCustom;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;

/**
 * 
 * @author m.wannas
 *
 */
public class TypePieceReglementRepositoryImpl extends ListRepositoryImpl<TypePieceReglement,QTypePieceReglement>  implements TypePieceReglementRepositoryCustom, GridRepositoryCustom<TypePieceReglementBean> {

	  @Autowired
	    private CustomModelMapper mapper;
	    private final JPAQueryFactory queryFactory;

	    @Autowired
	    public TypePieceReglementRepositoryImpl(JPAQueryFactory queryFactory) {
	        this.queryFactory = queryFactory;
	    }

	    @Override
	    public GridListBean<TypePieceReglementBean> findEntities(PageDataBean pageDataBean, Map<String, ColumnDef> columnDefMap){
	        QTypePieceReglement pieceReglements = QTypePieceReglement.typePieceReglement;

	        JPAQuery<TypePieceReglement> query = queryFactory.selectFrom(pieceReglements);

	        // Sorting
	        applySort(query, pageDataBean, pieceReglements, columnDefMap);

	        // Filtering
	        applyFilterAndQuickSearch(query, pageDataBean, pieceReglements, columnDefMap);

	        // Count
	        Long totalCount = Long.valueOf(query.fetchCount());

	        // Pagination
	        QueryUtils.applyPagination(query, pageDataBean.getPageNumber(), pageDataBean.getPageSize());

	        // Result
	        List<TypePieceReglement> pieceReglementList = query.fetch();

	        GridListBean<TypePieceReglementBean> result = new GridListBean<TypePieceReglementBean>();

	        result.setTotalItems(totalCount);

	        List<TypePieceReglementBean> banqueBeans = mapper.map(pieceReglementList, TypePieceReglementBean.LIST_BEAN_TYPE);
	        result.getData().addAll(banqueBeans);

	        return result;
	    }

}
