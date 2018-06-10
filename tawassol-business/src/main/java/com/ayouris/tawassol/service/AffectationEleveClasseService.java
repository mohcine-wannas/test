package com.ayouris.tawassol.service;

import com.ayouris.tawassol.common.model.entity.AffectationEleveClasse;
import com.ayouris.tawassol.common.model.entity.Eleve;

/**
 * 
 * @author m.wannas
 *
 */

public interface AffectationEleveClasseService extends  GenericService<AffectationEleveClasse,Long> {


    AffectationEleveClasse getClasseByEleve(Eleve eleve);
}
