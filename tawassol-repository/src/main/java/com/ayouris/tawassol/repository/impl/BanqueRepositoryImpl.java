package com.ayouris.tawassol.repository.impl;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.*;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.Banque;
import com.ayouris.tawassol.common.model.entity.QBanque;
import com.ayouris.tawassol.common.util.QueryUtils;
import com.ayouris.tawassol.repository.custom.BanqueRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by YounesM on 05/05/2017.
 */
public class BanqueRepositoryImpl implements BanqueRepositoryCustom{

    @Autowired
    private CustomModelMapper mapper;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public BanqueRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public BanqueListBean findBanques(PageDataBean pageDataBean, Map<String, ColumnDef> columnDefMap){
        QBanque banques = QBanque.banque;

        JPAQuery<Banque> query = queryFactory.selectFrom(banques);

        // Sorting
        applySort(query, pageDataBean, banques, columnDefMap);

        // Filtering
        applyFilterAndQuickSearch(query, pageDataBean, banques, columnDefMap);

        // Count
        Long totalCount = Long.valueOf(query.fetchCount());

        // Pagination
        QueryUtils.applyPagination(query, pageDataBean.getPageNumber(), pageDataBean.getPageSize());

        // Result
        List<Banque> banqueList = query.fetch();

        BanqueListBean result = new BanqueListBean();

        result.setTotalItems(totalCount);

        List<BanqueBean> banqueBeans = mapper.map(banqueList, BanqueBean.LIST_BEAN_TYPE);
        result.getData().addAll(banqueBeans);

        return result;
    }

    private void applySort(JPAQuery query, PageDataBean pageDataBean, QBanque banque,
                           Map<String, ColumnDef> columnDefMap) {
        String sortColumn = pageDataBean.getSortColumn();
        if (pageDataBean.isSortingDefined() && columnDefMap.containsKey(sortColumn)) {
            Order direction = Order.valueOf(pageDataBean.getSortOrder().name());

            try {
                QBanque o = banque;
                Class<?> c = o.getClass();
                Field f = c.getDeclaredField(sortColumn);

                f.setAccessible(true);

                Expression orderExp = (Expression) f.get(o);

                query.orderBy(new OrderSpecifier<>(direction, orderExp));
            } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace(); // TODO Logger or throw
            }

        } else {
            query.orderBy(new OrderSpecifier<>(Order.ASC, banque.createdOn));
        }
    }

    private void applyFilterAndQuickSearch(JPAQuery<Banque> query, PageDataBean pageDataBean, QBanque banques,
                                           Map<String, ColumnDef> columnDefMap) {

        BooleanExpression matchFilter = applyFilter(query, pageDataBean, banques, columnDefMap);
        BooleanExpression matchQuickFilter = applyQuickSearch(query, pageDataBean, banques, columnDefMap);
        BooleanExpression whereExpression = null;
        if (matchFilter != null && matchQuickFilter != null) {
            whereExpression = matchFilter.and(matchQuickFilter);
        } else if (matchFilter != null) {
            whereExpression = matchFilter;
        } else if (matchQuickFilter != null) {
            whereExpression = matchQuickFilter;
        }

        if (whereExpression != null) {
            query.where(whereExpression);
        }
    }

    private BooleanExpression applyQuickSearch(JPAQuery<Banque> query, PageDataBean pageDataBean, QBanque banques,
                                               Map<String, ColumnDef> columnDefMap) {

        BooleanExpression matchQuickFilter = null;

        if (pageDataBean.isQuickFilterDefined()) {

            String QuickFilterValue = pageDataBean.getQuickFilter();

            for (Map.Entry<String, ColumnDef> entry : columnDefMap.entrySet()) {
                try {

                    String field = entry.getKey();
                    ColumnDef columnDef = entry.getValue();

                    QBanque o = banques;
                    Class<?> c = o.getClass();

                    Field f = c.getDeclaredField(field);

                    f.setAccessible(true);

                    BooleanExpression exp = null;

                    switch (columnDef.getType()) {
                        case NUMBER:
                            NumberPath numberExp = (NumberPath) f.get(o);
                            exp = numberExp.like(QuickFilterValue);
                            break;
                        case TEXT:
                            StringPath stringExp = (StringPath) f.get(o);
                            exp = stringExp.containsIgnoreCase(QuickFilterValue);
                            break;
                        case ENUM:
                            EnumPath enumPath  = (EnumPath) f.get(o);
                            exp = enumPath.eq(QuickFilterValue);
                            break;
                        case SELECT:
                            stringExp = (StringPath) f.get(o);
                            exp = stringExp.containsIgnoreCase(QuickFilterValue);
                            break;
                        default:
                            break;
                    }
                    if (exp != null) {
                        matchQuickFilter = matchQuickFilter == null ? exp : matchQuickFilter.or(exp);
                    }
                } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
                        | IllegalAccessException e) {
                    e.printStackTrace(); // TODO Logger or throw
                }

            }
        }
        return matchQuickFilter;

    }

    private BooleanExpression applyFilter(JPAQuery<Banque> query, PageDataBean pageDataBean, QBanque banques,
                                          Map<String, ColumnDef> columnDefMap) {

        BooleanExpression currentMatchFilter = null, matchFilter = null;

        if (pageDataBean.isFiltersDefined()) {

            QBanque o = banques;
            Class<?> c = o.getClass();

            matchFilter = null;
            for (DataFilterBean dataFilter : pageDataBean.getFilters()) {

                currentMatchFilter = null;
                String column = dataFilter.getColumn();

                try {
                    Field f = c.getDeclaredField(column);

                    f.setAccessible(true);

                    BooleanExpression exp = null;

                    ColumnDef columnDef = columnDefMap.get(column);
                    if (columnDef != null) {
                        switch (columnDef.getType()) {
                            case CHECKBOX:
                                BooleanPath booleanExp = (BooleanPath) f.get(o);
                                exp = booleanExp.eq(dataFilter.getBooleanValue());
                                currentMatchFilter = currentMatchFilter == null ? exp : currentMatchFilter.and(exp);
                                break;
                            case DATE:
                                @SuppressWarnings("unchecked")
                                DateTimePath<Date> dateExp = (DateTimePath<Date>) f.get(o);
                                DateFilterValue dateValue = dataFilter.getDateValue();
                                currentMatchFilter = dateExp.between(dateValue.getDate1(), dateValue.getDate2());
                                break;
                            case TEXT:
                                StringPath StringExp = (StringPath) f.get(o);
                                currentMatchFilter = StringExp.containsIgnoreCase(dataFilter.getStringValue());
                                break;
                            case SELECT:
                                StringExp = (StringPath) f.get(o);
                                currentMatchFilter = StringExp.equalsIgnoreCase(dataFilter.getStringValue());
                                break;
                            default:
                                break;
                        }

                        matchFilter = matchFilter == null ? currentMatchFilter : matchFilter.and(currentMatchFilter);

                    }
                } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException
                        | SecurityException e) {
                    e.printStackTrace(); // TODO Logger or throw
                }

            }

        }
        return matchFilter;
    }
}
