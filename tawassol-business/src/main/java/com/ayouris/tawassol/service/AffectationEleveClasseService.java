package com.ayouris.tawassol.service;

import com.ayouris.tawassol.common.model.entity.AffectationEleveClasse;

import java.util.Optional;

/**
 * 
 * @author m.wannas
 *
 */

public interface AffectationEleveClasseService extends  GenericService<AffectationEleveClasse,Long> {


    AffectationEleveClasse findByEleveIdAndClasseId(Long eleveId, Long classeId);
}
