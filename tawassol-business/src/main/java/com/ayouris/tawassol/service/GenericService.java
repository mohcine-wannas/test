package com.ayouris.tawassol.service;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ayouris.tawassol.common.model.entity.generic.BaseEntity;

/**
 * Created by chamakh on 06/01/2017.
 */
public interface GenericService<T extends BaseEntity, ID extends Serializable> extends JpaRepository<T, ID> {
}
