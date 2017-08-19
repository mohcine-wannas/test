package com.ayouris.tawassol.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.CamionBean;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.Camion;
import com.ayouris.tawassol.common.model.entity.QCamion;
import com.ayouris.tawassol.common.util.QueryUtils;
import com.ayouris.tawassol.repository.custom.CamionRepositoryCustom;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;

/**
 * 
 * @author m.wannas
 *
 */
public class CamionRepositoryImpl extends ListRepositoryImpl<Camion,QCamion>  implements CamionRepositoryCustom, GridRepositoryCustom<CamionBean> {

	  @Autowired
	    private CustomModelMapper mapper;
	    private final JPAQueryFactory queryFactory;

	    @Autowired
	    public CamionRepositoryImpl(JPAQueryFactory queryFactory) {
	        this.queryFactory = queryFactory;
	    }

	    @Override
	    public GridListBean<CamionBean> findEntities(PageDataBean pageDataBean, Map<String, ColumnDef> columnDefMap){
	        QCamion camions = QCamion.camion;

	        JPAQuery<Camion> query = queryFactory.selectFrom(camions);

	        // Sorting
	        applySort(query, pageDataBean, camions, columnDefMap);

	        // Filtering
	        applyFilterAndQuickSearch(query, pageDataBean, camions, columnDefMap);

	        // Count
	        Long totalCount = Long.valueOf(query.fetchCount());

	        // Pagination
	        QueryUtils.applyPagination(query, pageDataBean.getPageNumber(), pageDataBean.getPageSize());

	        // Result
	        List<Camion> camionList = query.fetch();

	        GridListBean<CamionBean> result = new GridListBean<CamionBean>();

	        result.setTotalItems(totalCount);

	        List<CamionBean> banqueBeans = mapper.map(camionList, CamionBean.LIST_BEAN_TYPE);
	        result.getData().addAll(banqueBeans);

	        return result;
	    }

}
