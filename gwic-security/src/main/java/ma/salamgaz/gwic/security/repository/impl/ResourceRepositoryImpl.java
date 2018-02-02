package ma.salamgaz.gwic.security.repository.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import ma.salamgaz.gwic.admin.model.entity.QResource;
import ma.salamgaz.gwic.admin.model.entity.QRole;
import ma.salamgaz.gwic.admin.model.enums.PermissionType;
import ma.salamgaz.gwic.common.model.bean.DataCriteriaBean;
import ma.salamgaz.gwic.common.model.bean.DataFilterBean;
import ma.salamgaz.gwic.security.repository.CustomResourceRepository;

@ComponentScan("com.querydsl.jpa")
public class ResourceRepositoryImpl implements CustomResourceRepository {
    
	private final JPAQueryFactory queryFactory;
	private final QRole role = QRole.role;
	private final QResource resource = QResource.resource;

	@Autowired
	public ResourceRepositoryImpl(JPAQueryFactory queryFactory) {
		super();
		this.queryFactory = queryFactory;
	}

//    @Override
//    public ResultListBean<ResourceBean> getRoleResources(Long roleId, DataCriteriaBean criteria) {
//
//        JPAQuery<Resource> resourceQuery = queryFactory.selectFrom(resource);
//
//        // Sorting
//        applySort(resourceQuery, criteria, resource);
//
//        // Sub Query
//        /*
//        JPASubQuery roleQuery = new JPASubQuery();
//        QPermissionRight permissionRight = new QPermissionRight("permissionRight");
//        QResource roleResource = new QResource("roleResource");
//        roleQuery.from(role);
//        roleQuery.innerJoin(role.permissions, permissionRight).innerJoin(permissionRight.resource, roleResource);
//        roleQuery.where(role.id.eq(roleId).and(roleResource.id.eq(resource.id)));
//        roleQuery.groupBy(role.id);
//
//        // Filtering
//        applyFilter(resourceQuery, roleQuery, criteria, resource);
//
//        // Result
//        List<ResourceBean> resourceBeans = resourceQuery
//                .list(ConstructorExpression.create(ResourceBean.class, resource.id, resource.name.as("name"),
//                        resource.description.as("description"), roleQuery.count().as("permissionType")));
//
//        ResultListBean<ResourceBean> result = new ResultListBean<>(resourceBeans, Long.valueOf(resourceBeans.size()));
//
//        return result;
//        */
//        return null;
//
//    }

    private void applyFilter(JPAQuery query, JPQLQuery subQuery, DataCriteriaBean criteria, QResource resource) {
        BooleanExpression filterPredicate = null;
        if (criteria.isFiltersDefined()) {
            for (DataFilterBean dataFilter : criteria.getFilters()) {
                BooleanExpression matchFilter = null;
                switch (dataFilter.getColumn()) {

                case "name":
                    matchFilter = resource.name.containsIgnoreCase(dataFilter.getValue());
                    break;

                case "description":
                    matchFilter = resource.description.containsIgnoreCase(dataFilter.getValue());
                    break;

//                case "permissionType":
//                    matchFilter = permissionTypeFilter(subQuery, dataFilter.getValue());
//                    break;

                default:
                    break;
                }
                filterPredicate = filterPredicate == null ? matchFilter : filterPredicate.and(matchFilter);
            }
            query.where(filterPredicate);
        }
    }

    private BooleanExpression permissionTypeFilter(JPQLQuery subQuery, String stringPermissionType) {

        PermissionType permissionType = PermissionType.valueOf(stringPermissionType);
        BooleanExpression matchFilter = null;
//        switch (permissionType) {
//        case READ:
//            matchFilter = subQuery.count().eq(1L);
//            break;
//        case WRITE:
//            matchFilter = subQuery.count().gt(1L);
//            break;
//        case NONE:
//            matchFilter = subQuery.count().isNull();
//            break;
//        default:
//            break;
//        }
        return matchFilter;
    }

    private void applySort(JPAQuery query, DataCriteriaBean criteria, QResource resource) {
//        if (criteria.isSortingDefined()) {
//            Order direction = Order.valueOf(criteria.getSortOrder().toUpperCase());
//
//            StringExpression orderExp = new StringPath(criteria.getSortColumn());
//
//            OrderSpecifier<String> orderBy = new OrderSpecifier<>(direction, orderExp);
//
//            orderBy = Order.DESC.equals(direction) ? orderBy.nullsLast() : orderBy.nullsFirst();
//
//            query.orderBy(orderBy);
//
//        } else {
            query.orderBy(new OrderSpecifier<>(Order.ASC, resource.name));
//        }
    }
}
