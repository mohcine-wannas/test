package ma.salamgaz.tawassol.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import ma.salamgaz.tawassol.common.mapper.CustomModelMapper;
import ma.salamgaz.tawassol.common.model.bean.TypeFileAttenteBean;
import ma.salamgaz.tawassol.common.model.bean.GridListBean;
import ma.salamgaz.tawassol.common.model.bean.PageDataBean;
import ma.salamgaz.tawassol.common.model.entity.ColumnDef;
import ma.salamgaz.tawassol.common.model.entity.TypeFileAttente;
import ma.salamgaz.tawassol.common.model.entity.QTypeFileAttente;
import ma.salamgaz.tawassol.common.util.QueryUtils;
import ma.salamgaz.tawassol.repository.custom.TypeFileAttenteRepositoryCustom;
import ma.salamgaz.tawassol.repository.custom.GridRepositoryCustom;

/**
 * 
 * @author m.wannas
 *
 */
public class TypeFileAttenteRepositoryImpl extends ListRepositoryImpl<TypeFileAttente,QTypeFileAttente>  implements TypeFileAttenteRepositoryCustom, GridRepositoryCustom<TypeFileAttenteBean> {

	  @Autowired
	    private CustomModelMapper mapper;
	    private final JPAQueryFactory queryFactory;

	    @Autowired
	    public TypeFileAttenteRepositoryImpl(JPAQueryFactory queryFactory) {
	        this.queryFactory = queryFactory;
	    }

	    @Override
	    public GridListBean<TypeFileAttenteBean> findEntities(PageDataBean pageDataBean, Map<String, ColumnDef> columnDefMap){
	        QTypeFileAttente typeFileAttentes = QTypeFileAttente.typeFileAttente;

	        JPAQuery<TypeFileAttente> query = queryFactory.selectFrom(typeFileAttentes);

	        // Sorting
	        applySort(query, pageDataBean, typeFileAttentes, columnDefMap);

	        // Filtering
	        applyFilterAndQuickSearch(query, pageDataBean, typeFileAttentes, columnDefMap);

	        // Count
	        Long totalCount = Long.valueOf(query.fetchCount());

	        // Pagination
	        QueryUtils.applyPagination(query, pageDataBean.getPageNumber(), pageDataBean.getPageSize());

	        // Result
	        List<TypeFileAttente> typeFileAttenteList = query.fetch();

	        GridListBean<TypeFileAttenteBean> result = new GridListBean<TypeFileAttenteBean>();

	        result.setTotalItems(totalCount);

	        List<TypeFileAttenteBean> banqueBeans = mapper.map(typeFileAttenteList, TypeFileAttenteBean.LIST_BEAN_TYPE);
	        result.getData().addAll(banqueBeans);

	        return result;
	    }

}
