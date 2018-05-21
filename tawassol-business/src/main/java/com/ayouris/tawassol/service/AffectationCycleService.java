package com.ayouris.tawassol.service;

import com.ayouris.tawassol.common.model.bean.AffectationCycleBean;
import com.ayouris.tawassol.common.model.bean.CycleBean;
import com.ayouris.tawassol.common.model.entity.AffectationCycle;
import com.ayouris.tawassol.common.model.entity.AnneeScolaire;
import com.ayouris.tawassol.common.model.entity.Cycle;
import com.ayouris.tawassol.common.model.entity.School;

import java.util.List;

/**
 * @author m.wannas
 */

public interface AffectationCycleService extends GenericService<AffectationCycle, Long> {

    AffectationCycleBean getCurrentAffectationCycleBean();

    AffectationCycle getAffectationCycleBySchoolAndCycleAndAnneeScolaire(School school, Cycle cycle, AnneeScolaire anneeScolaire);

    List<AffectationCycle> getAffectationsCycleBySchool(School school);

    AffectationCycle getAffectationCycleByCycleIdAndSchoolIdAndAnneeScolaireId(Long cycleId, Long schoolId, Long anneeScolaireId);

    Long save(AffectationCycleBean affectationCycle);

    AffectationCycle generateDefaultAffectationCycle(School school, Cycle cycle, AnneeScolaire currentAnneeScolaire);

    AffectationCycle generateDefaultAffectationCycle(School school, CycleBean cycleBean,
                                                     AnneeScolaire currentAnneeScolaire);

    AffectationCycleBean getAffectationCycleBySchoolCodeAndByCycleId(String schoolCode, Long cycleId);

    AffectationCycle getAffectationCycleBySchoolIdAndByCycleId(Long schoolId, Long cycleId);

}
