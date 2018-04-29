package com.ayouris.tawassol.repository;

import com.ayouris.tawassol.common.model.entity.AffectationParentEleve;
import com.ayouris.tawassol.common.model.entity.Parent;
import com.ayouris.tawassol.common.repository.CommonRepository;

import java.util.List;

public interface AffectationParentEleveRepository extends  CommonRepository<AffectationParentEleve> {


    List<AffectationParentEleve> findByParentId(Long id);
}
