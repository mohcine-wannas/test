package com.ayouris.tawassol.security.repository;

import com.ayouris.tawassol.security.repository.CustomResourceRepository;

import org.springframework.stereotype.Repository;

import com.ayouris.tawassol.admin.model.entity.Resource;
import com.ayouris.tawassol.common.repository.CommonRepository;

@Repository
public interface ResourceRepository extends CommonRepository<Resource>, CustomResourceRepository {

}
