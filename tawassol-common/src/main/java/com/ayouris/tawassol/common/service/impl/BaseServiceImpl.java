package com.ayouris.tawassol.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ayouris.tawassol.common.model.entity.generic.BaseEntity;
import com.ayouris.tawassol.common.service.BaseService;


	public abstract class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {

	    @Autowired
	    private JpaRepository<T, Long> jpaRepository;

	    @Override
	    public List<T> findAll() {
	        return jpaRepository.findAll();
	    }

	    @Override
	    public T save(T s) {
	        return jpaRepository.save(s);
	    }
	    
	    @Override
	    public T update(T s) {
	        return jpaRepository.save(s);
	    }

	    @Override
	    public T findOne(Long id) {
	        return jpaRepository.findOne(id);
	    }

	    @Override
	    public void delete(Long id) {
	        jpaRepository.delete(id);

	    }
	    }

