package ma.salamgaz.gwic.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import ma.salamgaz.gwic.common.mapper.CustomModelMapper;
import ma.salamgaz.gwic.common.model.bean.GridListBean;
import ma.salamgaz.gwic.common.model.bean.PageDataBean;
import ma.salamgaz.gwic.common.model.bean.TransporteurBean;
import ma.salamgaz.gwic.common.model.entity.ColumnDef;
import ma.salamgaz.gwic.common.model.entity.QTransporteur;
import ma.salamgaz.gwic.common.model.entity.Transporteur;
import ma.salamgaz.gwic.common.util.QueryUtils;
import ma.salamgaz.gwic.repository.custom.GridRepositoryCustom;
import ma.salamgaz.gwic.repository.custom.TransporteurRepositoryCustom;

/**
 * 
 * @author m.wannas
 *
 */
public class TransporteurRepositoryImpl extends ListRepositoryImpl<Transporteur,QTransporteur>  implements TransporteurRepositoryCustom, GridRepositoryCustom<TransporteurBean> {

	  @Autowired
	    private CustomModelMapper mapper;
	    private final JPAQueryFactory queryFactory;

	    @Autowired
	    public TransporteurRepositoryImpl(JPAQueryFactory queryFactory) {
	        this.queryFactory = queryFactory;
	    }

	    @Override
	    public GridListBean<TransporteurBean> findEntities(PageDataBean pageDataBean, Map<String, ColumnDef> columnDefMap){
	        QTransporteur Transporteurs = QTransporteur.transporteur;

	        JPAQuery<Transporteur> query = queryFactory.selectFrom(Transporteurs);

	        // Sorting
	        applySort(query, pageDataBean, Transporteurs, columnDefMap);

	        // Filtering
	        applyFilterAndQuickSearch(query, pageDataBean, Transporteurs, columnDefMap);

	        // Count
	        Long totalCount = Long.valueOf(query.fetchCount());

	        // Pagination
	        QueryUtils.applyPagination(query, pageDataBean.getPageNumber(), pageDataBean.getPageSize());

	        // Result
	        List<Transporteur> TransporteurList = query.fetch();

	        GridListBean<TransporteurBean> result = new GridListBean<TransporteurBean>();

	        result.setTotalItems(totalCount);

	        List<TransporteurBean> banqueBeans = mapper.map(TransporteurList, TransporteurBean.LIST_BEAN_TYPE);
	        result.getData().addAll(banqueBeans);

	        return result;
	    }

}
