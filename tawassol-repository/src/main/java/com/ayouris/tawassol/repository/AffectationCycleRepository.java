package com.ayouris.tawassol.repository;

import com.ayouris.tawassol.common.model.entity.AffectationCycle;
import com.ayouris.tawassol.common.repository.CommonRepository;
import org.springframework.context.annotation.Primary;

import java.util.List;
import java.util.Optional;

@Primary
public interface AffectationCycleRepository extends CommonRepository<AffectationCycle> {

    List<AffectationCycle> findBySchoolIdAndAnneeScolaireIdOrderByOrderAsc(Long schoolId, Long anneeScolaireId);

    Optional<AffectationCycle> findByCycleIdAndSchoolIdAndAnneeScolaireId(Long cycleId, Long schoolId, Long anneeScolaireId);

}
