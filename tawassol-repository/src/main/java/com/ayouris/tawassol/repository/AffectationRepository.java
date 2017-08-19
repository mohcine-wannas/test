package com.ayouris.tawassol.repository;

import java.util.List;

import com.ayouris.tawassol.common.model.entity.Affectation;
import com.ayouris.tawassol.common.model.enums.TypeChargement;
import com.ayouris.tawassol.common.repository.CommonRepository;
import com.ayouris.tawassol.repository.custom.AffectationRepositoryCustom;

public interface AffectationRepository extends  CommonRepository<Affectation>,AffectationRepositoryCustom {


	List<Affectation> findBySiteIdAndClientIdAndConcessionnaireIdAndProduitTypeChargement(Long siteId, Long clientId,
			Long concessionnaireId, TypeChargement vrac);

	
}
