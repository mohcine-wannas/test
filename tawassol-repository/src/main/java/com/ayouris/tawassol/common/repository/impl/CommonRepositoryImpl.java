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
import com.ayouris.tawassol.common.repository.CommonRepository;
import com.ayouris.tawassol.common.repository.entitygraph.EntityGraphGenerator;
import com.ayouris.tawassol.common.util.QueryUtils;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.jpa.impl.JPAQuery;

public class CommonRepositoryImpl<T extends BaseEntity> extends QueryDslJpaRepository<T, Long>
		implements CommonRepository<T> {

	private final EntityManager entityManager;


	public CommonRepositoryImpl(JpaEntityInformation<T, Long> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityManager = entityManager;
	}

	@Override
	public Long getSequenceNextVal(String sequenceName) {
		return ((BigInteger) entityManager.createNativeQuery(sequenceName + ".nextval").getSingleResult()).longValue();
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
