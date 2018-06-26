package com.ayouris.tawassol.service;

import com.ayouris.tawassol.common.model.bean.AffectationUniteBean;
import com.ayouris.tawassol.common.model.entity.AffectationUnite;

import java.util.List;

public interface AffectationUniteService extends GenericService<AffectationUnite, Long> {

    List<AffectationUniteBean> getAffectationsUniteByCycleId(Long cycleId);

    void updateAffectationsUnite(List<AffectationUniteBean> affectations);

    List<AffectationUniteBean> getAffectationsUniteBySchoolCodeAndByCycleIdAndCurrentAnneeScolaire(String schoolCode, Long cycleId);
}
