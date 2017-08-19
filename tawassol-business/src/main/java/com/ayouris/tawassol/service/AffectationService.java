package com.ayouris.tawassol.service;

import java.util.List;

import com.ayouris.tawassol.common.model.bean.AffectationBean;
import com.ayouris.tawassol.common.model.entity.Affectation;
import com.ayouris.tawassol.common.model.enums.TypeChargement;

/**
 * 
 * @author m.wannas
 *
 */

public interface AffectationService extends GenericService<Affectation, Long>, RefService<AffectationBean> {

	List<AffectationBean> getAll();

	List<AffectationBean> findBySiteIdAndClientIdAndConcessionnaireIdAndProduitTypeChargement(Long siteId,
			Long clientId, Long concessionnaireId, TypeChargement typeChargement);
}
