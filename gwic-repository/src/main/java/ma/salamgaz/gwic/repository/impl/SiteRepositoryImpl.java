package ma.salamgaz.gwic.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import ma.salamgaz.gwic.common.mapper.CustomModelMapper;
import ma.salamgaz.gwic.common.model.bean.GridListBean;
import ma.salamgaz.gwic.common.model.bean.PageDataBean;
import ma.salamgaz.gwic.common.model.bean.SiteBean;
import ma.salamgaz.gwic.common.model.entity.ColumnDef;
import ma.salamgaz.gwic.common.model.entity.QSite;
import ma.salamgaz.gwic.common.model.entity.Site;
import ma.salamgaz.gwic.common.util.QueryUtils;
import ma.salamgaz.gwic.repository.custom.GridRepositoryCustom;
import ma.salamgaz.gwic.repository.custom.SiteRepositoryCustom;

public class SiteRepositoryImpl extends ListRepositoryImpl<Site,QSite> implements SiteRepositoryCustom , GridRepositoryCustom<SiteBean>{

    @Autowired
    private CustomModelMapper mapper;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public SiteRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

	@Override
	public GridListBean<SiteBean> findEntities(PageDataBean paginateData, Map<String, ColumnDef> columnDefMap) {

		QSite sites = QSite.site;
		
		JPAQuery<Site> query =  queryFactory.selectFrom(sites);
		 

		// Sorting
		applySort(query, paginateData, sites, columnDefMap);

		// filtering

		applyFilterAndQuickSearch(query, paginateData, sites, columnDefMap);

		// count
		Long totalCount = Long.valueOf(query.fetchCount());

		// pagination
		QueryUtils.applyPagination(query, paginateData.getPageNumber(), paginateData.getPageSize());


        // Result
        List<Site> siteList = query.fetch();

        GridListBean<SiteBean> result = new GridListBean<SiteBean>();

        result.setTotalItems(totalCount);
        List<SiteBean>siteBeans = mapper.map(siteList, SiteBean.LIST_BEAN_TYPE);
        result.getData().addAll(siteBeans);
        
        return result;
	}











}
