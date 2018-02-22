package com.ayouris.tawassol.service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * Created by chamakh on 06/01/2017.
 */
public interface GenericService<T, ID extends Serializable> extends JpaRepository<T, ID> {
}
