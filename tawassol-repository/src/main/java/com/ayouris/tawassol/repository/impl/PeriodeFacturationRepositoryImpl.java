package com.ayouris.tawassol.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.jpa.impl.JPAQueryFactory;

import com.ayouris.tawassol.common.model.entity.PeriodeFacturation;
import com.ayouris.tawassol.common.model.entity.QPeriodeFacturation;
import com.ayouris.tawassol.repository.custom.PeriodeFacturationRepositoryCustom;

/**
 * 
 * @author m.wannas
 *
 */
public class PeriodeFacturationRepositoryImpl extends ListRepositoryImpl<PeriodeFacturation, QPeriodeFacturation>
		implements PeriodeFacturationRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Autowired
	public PeriodeFacturationRepositoryImpl(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

}
