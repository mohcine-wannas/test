package ma.salamgaz.gwic.repository.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.BooleanPath;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.EnumPath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQuery;

import ma.salamgaz.gwic.common.model.bean.DataFilterBean;
import ma.salamgaz.gwic.common.model.bean.DateFilterValue;
import ma.salamgaz.gwic.common.model.bean.PageDataBean;
import ma.salamgaz.gwic.common.model.entity.ColumnDef;

@SuppressWarnings("rawtypes")
public class ListRepositoryImpl<T,Q> {
	
	
	private static final String DEFAULT_SORT_COLUMN = "createdOn";

	public void applyFilterAndQuickSearch(JPAQuery<T> query, PageDataBean pageDataBean, Q entities,
			Map<String, ColumnDef> columnDefMap) {

		BooleanExpression matchFilter = applyFilter(query, pageDataBean, entities, columnDefMap);
		BooleanExpression matchQuickFilter = applyQuickSearch(query, pageDataBean, entities, columnDefMap);
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
	
	private BooleanExpression applyQuickSearch(JPAQuery<T> query, PageDataBean pageDataBean, Q entities,
			Map<String, ColumnDef> columnDefMap) {

		BooleanExpression matchQuickFilter = null;

		if (pageDataBean.isQuickFilterDefined()) {

			String QuickFilterValue = pageDataBean.getQuickFilter();

			for (Map.Entry<String, ColumnDef> entry : columnDefMap.entrySet()) {
				try {
					
					
					ColumnDef columnDef = entry.getValue();
					if(columnDef.getHide() != null && columnDef.getHide()) {
						continue;
					}
					
					String field = getField(entry.getKey(),columnDef);
					
					EntityPathBase o = getObject(columnDef,(EntityPathBase) entities);
					Class<?> c = o.getClass();
					
					Field f = c.getDeclaredField(field);

					f.setAccessible(true);

					BooleanExpression exp = null;

					switch (columnDef.getType()) {
					case NUMBER:
						NumberPath numberExp = (NumberPath) f.get(o);
						exp = numberExp.stringValue().containsIgnoreCase(QuickFilterValue);
						break;
					case TEXT:
						StringPath stringExp = (StringPath) f.get(o);
						exp = stringExp.containsIgnoreCase(QuickFilterValue);
						break;
					case ENUM:
						EnumPath enumPath = (EnumPath) f.get(o);
						exp = enumPath.stringValue().containsIgnoreCase(QuickFilterValue);
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
						| IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
					e.printStackTrace(); // TODO Logger or throw
				}

			}
		}
		return matchQuickFilter;

	}

	private String getField(String key, ColumnDef columnDef) {
			if(StringUtils.isNotBlank(columnDef.getNestedField())) {
				String[] objects = columnDef.getNestedField().split("\\.");
				return objects[objects.length-1];
			}else{
				return key;
			}
	}


	private EntityPathBase getObject(ColumnDef columnDef, EntityPathBase entities) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		if(StringUtils.isNotBlank(columnDef.getNestedField())) {
			String[] objects = columnDef.getNestedField().split("\\.");
			Object o = entities;
			for (int i = 0; i < objects.length-1; i++) {
				
				String propertyName = objects[i];
				Class<?> c = o.getClass();
				
				if(propertyName.endsWith("()")) {
					Method m = c.getDeclaredMethod(propertyName.replaceAll("\\(\\)", ""));
					m.setAccessible(true);
					o = m.invoke(o);
				}else {
					Field f = c.getDeclaredField(objects[i]);
					o =  f.get(o);
				}
			}
			return (EntityPathBase) o;
		}else{
			return entities;
		}
	}

	private BooleanExpression applyFilter(JPAQuery<T> query, PageDataBean pageDataBean, Q  entities,
			Map<String, ColumnDef> columnDefMap) {

		BooleanExpression currentMatchFilter = null, matchFilter = null;

		if (pageDataBean.isFiltersDefined()) {

			matchFilter = null;
			for (DataFilterBean dataFilter : pageDataBean.getFilters()) {
				try {
					
					currentMatchFilter = null;
					String column = dataFilter.getColumn();
					ColumnDef columnDef = columnDefMap.get(column);
					String field = getField(column,columnDef);
					EntityPathBase o = getObject(columnDef,(EntityPathBase) entities);
					Class<?> c = o.getClass();
					Field f = c.getDeclaredField(field);

					f.setAccessible(true);

					BooleanExpression exp = null;

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
						case ENUM:
							if(StringUtils.isBlank(dataFilter.getStringValue())) {
								continue;
							}
							EnumPath enumPath = (EnumPath) f.get(o);
							currentMatchFilter = enumPath.stringValue().eq(dataFilter.getStringValue());
							break;
						case TEXT:
							if(StringUtils.isBlank(dataFilter.getStringValue())) {
								continue;
							}
							StringPath StringExp = (StringPath) f.get(o);
							currentMatchFilter = StringExp.equalsIgnoreCase(dataFilter.getStringValue());
							break;
						case NUMBER:
							if(StringUtils.isBlank(dataFilter.getStringValue())) {
								continue;
							}
							NumberPath numberExp = (NumberPath) f.get(o);
							currentMatchFilter = numberExp.stringValue().eq(dataFilter.getStringValue());
							break;
						case SELECT:
							if(StringUtils.isBlank(dataFilter.getStringValue())) {
								continue;
							}
							StringExp = (StringPath) f.get(o);
							currentMatchFilter = StringExp.equalsIgnoreCase(dataFilter.getStringValue());
							break;
						default:
							break;
						}

						matchFilter = matchFilter == null ? currentMatchFilter : matchFilter.and(currentMatchFilter);

					}
				} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException
						| SecurityException | InvocationTargetException | NoSuchMethodException e) {
					e.printStackTrace(); // TODO Logger or throw
				}

			}

		}
		return matchFilter;
	}
	
	public void applySort(JPAQuery<T> query, PageDataBean pageDataBean, Q entities, Map<String, ColumnDef> columnDefMap) {
		String sortColumn = pageDataBean.getSortColumn();
		Q o = entities;
		Class<?> c = o.getClass();
		try {
			if (pageDataBean.isSortingDefined() && columnDefMap.containsKey(sortColumn)) {

				Order direction = Order.valueOf(pageDataBean.getSortOrder().name());

				Field f = c.getDeclaredField(sortColumn);

				f.setAccessible(true);

				Expression orderExp = (Expression) f.get(o);
				query.orderBy(new OrderSpecifier<>(direction, orderExp));

			} else {

				Field f = c.getDeclaredField(DEFAULT_SORT_COLUMN);
				f.setAccessible(true);
				Expression orderExp = (Expression) f.get(o);
				query.orderBy(new OrderSpecifier<>(Order.ASC, orderExp));

			}
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace(); // TODO Logger or throw
		}
	}
}
