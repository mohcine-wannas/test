package com.ayouris.tawassol.repository;

import com.ayouris.tawassol.common.model.entity.AffectationEleveClasse;
import com.ayouris.tawassol.common.repository.CommonRepository;

import java.util.Optional;

public interface AffectationEleveClasseRepository extends CommonRepository<AffectationEleveClasse> {

    Optional<AffectationEleveClasse> findByEleveIdAndClasseId(Long eleveId, Long classeId);
}
