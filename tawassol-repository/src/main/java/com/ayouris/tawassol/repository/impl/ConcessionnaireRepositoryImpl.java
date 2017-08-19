package com.ayouris.tawassol.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.ConcessionnaireBean;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.Concessionnaire;
import com.ayouris.tawassol.common.model.entity.QConcessionnaire;
import com.ayouris.tawassol.common.util.QueryUtils;
import com.ayouris.tawassol.repository.custom.ConcessionnaireRepositoryCustom;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;

/**
 * 
 * @author m.wannas
 *
 */
public class ConcessionnaireRepositoryImpl extends ListRepositoryImpl<Concessionnaire,QConcessionnaire>  implements ConcessionnaireRepositoryCustom, GridRepositoryCustom<ConcessionnaireBean> {

	  @Autowired
	    private CustomModelMapper mapper;
	    private final JPAQueryFactory queryFactory;

	    @Autowired
	    public ConcessionnaireRepositoryImpl(JPAQueryFactory queryFactory) {
	        this.queryFactory = queryFactory;
	    }

	    @Override
	    @Transactional
	    public GridListBean<ConcessionnaireBean> findEntities(PageDataBean pageDataBean, Map<String, ColumnDef> columnDefMap){
	        QConcessionnaire concessionnaires = QConcessionnaire.concessionnaire;

	        JPAQuery<Concessionnaire> query = queryFactory.selectFrom(concessionnaires);

	        // Sorting
	        applySort(query, pageDataBean, concessionnaires, columnDefMap);

	        // Filtering
	        applyFilterAndQuickSearch(query, pageDataBean, concessionnaires, columnDefMap);

	        // Count
	        Long totalCount = Long.valueOf(query.fetchCount());

	        // Pagination
	        QueryUtils.applyPagination(query, pageDataBean.getPageNumber(), pageDataBean.getPageSize());

	        // Result
	        List<Concessionnaire> concessionnaireList = query.fetch();

	        GridListBean<ConcessionnaireBean> result = new GridListBean<ConcessionnaireBean>();

	        result.setTotalItems(totalCount);
	        List<ConcessionnaireBean>concessionnaireBeans = mapper.map(concessionnaireList, ConcessionnaireBean.LIST_BEAN_TYPE);
	        result.getData().addAll(concessionnaireBeans);

	        return result;
	    }

}
