package com.ayouris.tawassol.repository;

import java.util.List;

import com.ayouris.tawassol.common.model.bean.ChauffeurBean;
import com.ayouris.tawassol.common.model.entity.Chauffeur;
import com.ayouris.tawassol.common.repository.CommonRepository;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;

/**
 * 
 * @author m.wannas
 *
 */
public interface ChauffeurRepository extends CommonRepository<Chauffeur>, GridRepositoryCustom<ChauffeurBean>{


	List<Chauffeur> findByConcessionnaireIdAndActiveTrue(Long id);
	
}
