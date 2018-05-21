package com.ayouris.tawassol.repository;

import com.ayouris.tawassol.common.model.entity.School;
import com.ayouris.tawassol.common.repository.CommonRepository;

import java.util.Optional;

public interface SchoolRepository extends  CommonRepository<School> {

    Optional<School> findByCode(String code);

	
}
