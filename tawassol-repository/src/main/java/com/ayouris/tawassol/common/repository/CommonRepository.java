package com.ayouris.tawassol.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.ayouris.tawassol.common.model.entity.generic.BaseEntity;


@NoRepositoryBean
public interface CommonRepository<T extends BaseEntity> extends JpaRepository<T, Long>, QueryDslPredicateExecutor<T> {

    
	/**
     * return next val for a sequence
     * 
     * @param sequenceName
     * @return
     */
    Long getSequenceNextVal(String sequenceName);
    
    /**
     * Find referential data by text
     * 
     * @param entityRef
     *            referential entity (ex: country_ref)
     * @param entityLang
     *            lang referential entity (ex: country__lang)
     * @param predicate
     *            predicate (where condition)
     * @param userLang
     *            user language
     * @param pageNumber
     *            page number
     * @param pageSize
     *            page size
     * @param orderByColumn
     *            column to be ordered with
     * @return List of referential data corresponding to search text
     */
    <O extends Comparable<O>, P extends BaseEntity> Iterable<T> findRefData(Class<T> entityRef, Class<P> entityLang,
            Predicate predicate, Integer pageNumber, Integer pageSize, String orderByColumn);

    <O extends Comparable<O>, P extends BaseEntity> Iterable<T> findRefDataWithCountry(Class<T> entityRef,
            Class<P> entityLang, Predicate predicate, Integer pageNumber, Integer pageSize, String orderByColumn,
            String userLang);
    
    <O extends Comparable<O>> Iterable<T> searchOrderedByEntityGraph(Predicate predicate, EntityPathBase<T> qObject,
            List<String> properties, Integer pageNumber, Integer pageSize, OrderSpecifier<O> order);

    Iterable<T> searchByEntityGraph(Predicate predicate, EntityPathBase<T> qObject, String... properties);
    
    T searchOneByEntityGraph(Predicate predicate, EntityPathBase<T> qObject, String... properties);

}