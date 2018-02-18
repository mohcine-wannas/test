package com.ayouris.tawassol.repository;

import java.util.List;

import org.springframework.context.annotation.Primary;

import com.ayouris.tawassol.common.model.entity.AffectationCycle;
import com.ayouris.tawassol.common.repository.CommonRepository;

@Primary
public interface AffectationCycleRepository extends  CommonRepository<AffectationCycle> {

	List<AffectationCycle> findBySchoolIdAndAnneeScolaireIdOrderByOrderAsc(Long schoolId, Long anneeScolaireId);
	
}
