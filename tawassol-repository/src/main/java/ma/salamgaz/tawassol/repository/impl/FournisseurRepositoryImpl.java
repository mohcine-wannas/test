package ma.salamgaz.tawassol.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import ma.salamgaz.tawassol.common.mapper.CustomModelMapper;
import ma.salamgaz.tawassol.common.model.bean.FournisseurBean;
import ma.salamgaz.tawassol.common.model.bean.GridListBean;
import ma.salamgaz.tawassol.common.model.bean.PageDataBean;
import ma.salamgaz.tawassol.common.model.entity.ColumnDef;
import ma.salamgaz.tawassol.common.model.entity.Fournisseur;
import ma.salamgaz.tawassol.common.model.entity.QFournisseur;
import ma.salamgaz.tawassol.common.util.QueryUtils;
import ma.salamgaz.tawassol.repository.custom.FournisseurRepositoryCustom;
import ma.salamgaz.tawassol.repository.custom.GridRepositoryCustom;

/**
 * 
 * @author m.wannas
 *
 */
public class FournisseurRepositoryImpl extends ListRepositoryImpl<Fournisseur,QFournisseur>  implements FournisseurRepositoryCustom, GridRepositoryCustom<FournisseurBean> {

	  @Autowired
	    private CustomModelMapper mapper;
	    private final JPAQueryFactory queryFactory;

	    @Autowired
	    public FournisseurRepositoryImpl(JPAQueryFactory queryFactory) {
	        this.queryFactory = queryFactory;
	    }

	    @Override
	    public GridListBean<FournisseurBean> findEntities(PageDataBean pageDataBean, Map<String, ColumnDef> columnDefMap){
	        QFournisseur fournisseurs = QFournisseur.fournisseur;

	        JPAQuery<Fournisseur> query = queryFactory.selectFrom(fournisseurs);

	        // Sorting
	        applySort(query, pageDataBean, fournisseurs, columnDefMap);

	        // Filtering
	        applyFilterAndQuickSearch(query, pageDataBean, fournisseurs, columnDefMap);

	        // Count
	        Long totalCount = Long.valueOf(query.fetchCount());

	        // Pagination
	        QueryUtils.applyPagination(query, pageDataBean.getPageNumber(), pageDataBean.getPageSize());

	        // Result
	        List<Fournisseur> fournisseurList = query.fetch();

	        GridListBean<FournisseurBean> result = new GridListBean<FournisseurBean>();

	        result.setTotalItems(totalCount);

	        List<FournisseurBean> banqueBeans = mapper.map(fournisseurList, FournisseurBean.LIST_BEAN_TYPE);
	        result.getData().addAll(banqueBeans);

	        return result;
	    }

}
