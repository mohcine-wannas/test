package com.ayouris.tawassol.service;

import com.ayouris.tawassol.common.model.entity.AffectationEleveClasse;
import com.ayouris.tawassol.common.model.entity.Eleve;

import java.util.Optional;

/**
 * 
 * @author m.wannas
 *
 */

public interface AffectationEleveClasseService extends  GenericService<AffectationEleveClasse,Long> {


    AffectationEleveClasse findByEleveIdAndClasseId(Long eleveId, Long classeId);
    AffectationEleveClasse getClasseByEleve(Eleve eleve);
}
