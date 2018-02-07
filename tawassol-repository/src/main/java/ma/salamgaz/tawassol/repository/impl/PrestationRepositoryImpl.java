package ma.salamgaz.tawassol.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import ma.salamgaz.tawassol.common.mapper.CustomModelMapper;
import ma.salamgaz.tawassol.common.model.bean.PrestationBean;
import ma.salamgaz.tawassol.common.model.bean.GridListBean;
import ma.salamgaz.tawassol.common.model.bean.PageDataBean;
import ma.salamgaz.tawassol.common.model.entity.ColumnDef;
import ma.salamgaz.tawassol.common.model.entity.Prestation;
import ma.salamgaz.tawassol.common.model.entity.QPrestation;
import ma.salamgaz.tawassol.common.util.QueryUtils;
import ma.salamgaz.tawassol.repository.custom.PrestationRepositoryCustom;
import ma.salamgaz.tawassol.repository.custom.GridRepositoryCustom;

/**
 * 
 * @author m.wannas
 *
 */
public class PrestationRepositoryImpl extends ListRepositoryImpl<Prestation,QPrestation>  implements PrestationRepositoryCustom, GridRepositoryCustom<PrestationBean> {

	  @Autowired
	    private CustomModelMapper mapper;
	    private final JPAQueryFactory queryFactory;

	    @Autowired
	    public PrestationRepositoryImpl(JPAQueryFactory queryFactory) {
	        this.queryFactory = queryFactory;
	    }

	    @Override
	    public GridListBean<PrestationBean> findEntities(PageDataBean pageDataBean, Map<String, ColumnDef> columnDefMap){
	        QPrestation prestations = QPrestation.prestation;

	        JPAQuery<Prestation> query = queryFactory.selectFrom(prestations);

	        // Sorting
	        applySort(query, pageDataBean, prestations, columnDefMap);

	        // Filtering
	        applyFilterAndQuickSearch(query, pageDataBean, prestations, columnDefMap);

	        // Count
	        Long totalCount = Long.valueOf(query.fetchCount());

	        // Pagination
	        QueryUtils.applyPagination(query, pageDataBean.getPageNumber(), pageDataBean.getPageSize());

	        // Result
	        List<Prestation> prestationList = query.fetch();

	        GridListBean<PrestationBean> result = new GridListBean<PrestationBean>();

	        result.setTotalItems(totalCount);

	        List<PrestationBean> banqueBeans = mapper.map(prestationList, PrestationBean.LIST_BEAN_TYPE);
	        result.getData().addAll(banqueBeans);

	        return result;
	    }

}
