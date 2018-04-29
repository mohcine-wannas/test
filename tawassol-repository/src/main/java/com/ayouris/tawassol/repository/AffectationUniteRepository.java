package com.ayouris.tawassol.repository;

import com.ayouris.tawassol.common.model.entity.AffectationUnite;
import com.ayouris.tawassol.common.repository.CommonRepository;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Primary
public interface AffectationUniteRepository extends CommonRepository<AffectationUnite> {

    List<AffectationUnite> findByUniteIdAndAffectationCycleId(Long uniteId, Long affectationCycleId);

    List<AffectationUnite> findByAffectationCycleId(Long affectationCycleId);

}
