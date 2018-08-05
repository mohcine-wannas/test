package com.ayouris.tawassol.repository;

import com.ayouris.tawassol.common.model.entity.AffectationUnite;
import com.ayouris.tawassol.common.model.entity.AffectationUniteProf;
import com.ayouris.tawassol.common.repository.CommonRepository;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Primary
public interface AffectationUniteProfRepository extends CommonRepository<AffectationUniteProf> {

    List<AffectationUniteProf> findByProfesseurIdAndEnabledTrueAndUniteActiveTrue(Long professeurId);
    List<AffectationUniteProf> findByProfesseurIdAndEnabledTrue(Long professeurId);
    List<AffectationUniteProf> findByProfesseurId(Long professeurId);
}
