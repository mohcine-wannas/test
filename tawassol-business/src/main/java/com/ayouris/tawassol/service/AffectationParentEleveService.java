package com.ayouris.tawassol.service;

import com.ayouris.tawassol.common.model.entity.AffectationParentEleve;
import com.ayouris.tawassol.common.model.entity.Parent;

import java.util.List;

/**
 * 
 * @author m.wannas
 *
 */

public interface AffectationParentEleveService extends  GenericService<AffectationParentEleve,Long> {

    List<AffectationParentEleve> findByParent(Parent parent);

//	Long createOrUpdate(AffectationParentEleveBean affectationParentBean);

}
