package com.ayouris.tawassol.repository;

import java.util.List;

import com.ayouris.tawassol.common.model.entity.AffectationNiveau;
import com.ayouris.tawassol.common.repository.CommonRepository;

public interface AffectationNiveauRepository extends  CommonRepository<AffectationNiveau> {

	List<AffectationNiveau> findByDefaultChoiceTrue();

	
}
