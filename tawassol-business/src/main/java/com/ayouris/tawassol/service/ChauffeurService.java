package com.ayouris.tawassol.service;

import java.util.List;

import com.ayouris.tawassol.common.model.bean.ChauffeurBean;
import com.ayouris.tawassol.common.model.entity.Chauffeur;

/**
 * 
 * @author m.wannas
 *
 */

public interface ChauffeurService extends GenericService<Chauffeur, Long>, RefService<ChauffeurBean> {


	List<ChauffeurBean> getAll();


	List<ChauffeurBean> findByConcessionnaireId(Long id);
	
}
