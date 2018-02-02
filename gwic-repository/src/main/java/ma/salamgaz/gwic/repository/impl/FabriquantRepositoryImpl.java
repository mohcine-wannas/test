package ma.salamgaz.gwic.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import ma.salamgaz.gwic.common.mapper.CustomModelMapper;
import ma.salamgaz.gwic.common.model.bean.FabriquantBean;
import ma.salamgaz.gwic.common.model.bean.GridListBean;
import ma.salamgaz.gwic.common.model.bean.PageDataBean;
import ma.salamgaz.gwic.common.model.entity.ColumnDef;
import ma.salamgaz.gwic.common.model.entity.Fabriquant;
import ma.salamgaz.gwic.common.model.entity.QFabriquant;
import ma.salamgaz.gwic.common.util.QueryUtils;
import ma.salamgaz.gwic.repository.custom.FabriquantRepositoryCustom;
import ma.salamgaz.gwic.repository.custom.GridRepositoryCustom;

/**
 * 
 * @author m.wannas
 *
 */
public class FabriquantRepositoryImpl extends ListRepositoryImpl<Fabriquant,QFabriquant>  implements FabriquantRepositoryCustom, GridRepositoryCustom<FabriquantBean> {

	  @Autowired
	    private CustomModelMapper mapper;
	    private final JPAQueryFactory queryFactory;

	    @Autowired
	    public FabriquantRepositoryImpl(JPAQueryFactory queryFactory) {
	        this.queryFactory = queryFactory;
	    }

	    @Override
	    public GridListBean<FabriquantBean> findEntities(PageDataBean pageDataBean, Map<String, ColumnDef> columnDefMap){
	        QFabriquant fabriquants = QFabriquant.fabriquant;

	        JPAQuery<Fabriquant> query = queryFactory.selectFrom(fabriquants);

	        // Sorting
	        applySort(query, pageDataBean, fabriquants, columnDefMap);

	        // Filtering
	        applyFilterAndQuickSearch(query, pageDataBean, fabriquants, columnDefMap);

	        // Count
	        Long totalCount = Long.valueOf(query.fetchCount());

	        // Pagination
	        QueryUtils.applyPagination(query, pageDataBean.getPageNumber(), pageDataBean.getPageSize());

	        // Result
	        List<Fabriquant> fabriquantList = query.fetch();

	        GridListBean<FabriquantBean> result = new GridListBean<FabriquantBean>();

	        result.setTotalItems(totalCount);

	        List<FabriquantBean> banqueBeans = mapper.map(fabriquantList, FabriquantBean.LIST_BEAN_TYPE);
	        result.getData().addAll(banqueBeans);

	        return result;
	    }

}
