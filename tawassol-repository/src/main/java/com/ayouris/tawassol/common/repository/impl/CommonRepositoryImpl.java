package com.ayouris.tawassol.common.repository.impl;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.annotations.QueryHints;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.QueryDslJpaRepository;

import com.ayouris.tawassol.common.model.entity.generic.BaseEntity;
import com.ayouris.tawassol.common.model.entity.lang.CountryLang;
import com.ayouris.tawassol.common.model.entity.lang.Lang;
import com.ayouris.tawassol.common.model.entity.lang.QLang;
import com.ayouris.tawassol.common.model.entity.lang.RefDataLang;
import com.ayouris.tawassol.common.model.entity.ref.CountryRef;
import com.ayouris.tawassol.common.model.entity.ref.RefData;
import com.ayouris.tawassol.common.repository.CommonRepository;
import com.ayouris.tawassol.common.repository.entitygraph.EntityGraphGenerator;
import com.ayouris.tawassol.common.util.QueryUtils;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.PathBuilder;

public class CommonRepositoryImpl<T extends BaseEntity> extends QueryDslJpaRepository<T, Long>
		implements CommonRepository<T> {

	private final EntityManager entityManager;
	private final JpaEntityInformation<T, Long> entityInformation;
	private final JPAQueryFactory queryFactory;

	private final QLang lang = QLang.lang;

	public CommonRepositoryImpl(JpaEntityInformation<T, Long> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityManager = entityManager;
		this.entityInformation = entityInformation;
		this.queryFactory = new JPAQueryFactory(entityManager);
	}

	@Override
	public Long getSequenceNextVal(String sequenceName) {
		return ((BigInteger) entityManager.createNativeQuery(sequenceName + ".nextval").getSingleResult()).longValue();
	}

	@Override
	public <O extends Comparable<O>, P extends BaseEntity> Iterable<T> findRefData(Class<T> entityRef,
			Class<P> entityLang, Predicate predicate, Integer pageNumber, Integer pageSize, String orderByColumn) {

		PathBuilder<T> entityRefPath = new PathBuilder<T>(entityRef, "entityRef");
		PathBuilder<P> entityLangPath = new PathBuilder<P>(entityLang, "entityLang");

		JPAQuery<T> query = queryFactory.selectFrom(entityRefPath);

		query.fetchJoin()
				.leftJoin(entityRefPath.getSet(RefData.COLUMNS.LOCALIZED_LABEL.getName(), entityLang), entityLangPath)
				.fetchJoin()
				.leftJoin(entityLangPath.getSet(RefDataLang.COLUMNS.LANG.getName(), Lang.class), (Path) lang).fetch();

		query.where(predicate);

		QueryUtils.applyPagination(query, pageNumber, pageSize);

		if (orderByColumn != null) {
			query.orderBy(new OrderSpecifier<>(Order.ASC, entityRefPath.getString(orderByColumn)));
		}

		return query.fetch();
	}

	@Override
	public <O extends Comparable<O>, P extends BaseEntity> Iterable<T> findRefDataWithCountry(Class<T> entityRef,
			Class<P> entityLang, Predicate predicate, Integer pageNumber, Integer pageSize, String orderByColumn,
			String userLang) {

		PathBuilder<T> entityRefPath = new PathBuilder<T>(entityRef, "entityRef");
		PathBuilder<P> entityLangPath = new PathBuilder<P>(entityLang, "entityLang");

		PathBuilder<CountryRef> countryRef = new PathBuilder<>(CountryRef.class, "countryRef");
		PathBuilder<CountryLang> countryLang = new PathBuilder<>(CountryLang.class, "countryLang");
		QLang langCountry = new QLang("langCountry");

		JPAQuery<T> query = queryFactory.selectFrom(entityRefPath);

		query.fetchJoin()
				.leftJoin(entityRefPath.getSet(RefData.COLUMNS.LOCALIZED_LABEL.getName(), entityLang), entityLangPath)
				.fetchJoin()
				.leftJoin(entityLangPath.getSet(RefDataLang.COLUMNS.LANG.getName(), Lang.class), (Path) lang)
				.fetchJoin().leftJoin(entityRefPath.get("country", CountryRef.class), countryRef).fetchJoin()
				.leftJoin(countryRef.get(RefData.COLUMNS.LOCALIZED_LABEL.getName(), CountryLang.class), countryLang)
				.fetchJoin()
				.leftJoin(countryLang.getSet(RefDataLang.COLUMNS.LANG.getName(), Lang.class), (Path) langCountry)
				.fetch();

		BooleanExpression predicateAll = langCountry.code.eq(userLang).and(predicate);

		query.where(predicateAll);

		QueryUtils.applyPagination(query, pageNumber, pageSize);

		if (orderByColumn != null) {
			query.orderBy(new OrderSpecifier<>(Order.ASC, entityRefPath.getString(orderByColumn)));
		}

		return query.fetch();
	}

	@Override
	public Iterable<T> searchByEntityGraph(Predicate predicate, EntityPathBase<T> qObject, String... properties) {
		return searchOrderedByEntityGraph(predicate, qObject, properties != null ? Arrays.asList(properties) : null,
				null, null, null);
	}

	@Override
	public T searchOneByEntityGraph(Predicate pred, EntityPathBase<T> qObject, String... attributeEntities) {
		Iterable<T> it = searchByEntityGraph(pred, qObject, attributeEntities);
		if (it != null) {
			Iterator<T> ite = it.iterator();
			if (ite != null && ite.hasNext()) {
				return ite.next();
			}
		}
		return null;
	}

	@Override
	public <O extends Comparable<O>> Iterable<T> searchOrderedByEntityGraph(Predicate predicate,
			EntityPathBase<T> qObject, List<String> properties, Integer pageNumber, Integer pageSize,
			OrderSpecifier<O> orders) {
		JPAQuery<T> query = new JPAQuery<T>(entityManager);

		if (properties != null && !properties.isEmpty()) {
			query = query.setHint(QueryHints.FETCHGRAPH, new EntityGraphGenerator().graphWithRequests(entityManager,
					qObject.getType(), properties.toArray(new String[properties.size()])));
		}
		query = query.from(qObject);
		if (predicate != null) {
			query = query.where(predicate);
		}
		if (orders != null) {
			query = query.orderBy(orders);
		}

		QueryUtils.applyPagination(query, pageNumber, pageSize);

		return query.fetch();
	}

}
