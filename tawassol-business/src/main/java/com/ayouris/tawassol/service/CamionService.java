package com.ayouris.tawassol.service;

import java.util.List;

import com.ayouris.tawassol.common.model.bean.CamionBean;
import com.ayouris.tawassol.common.model.entity.Camion;

/**
 * 
 * @author m.wannas
 *
 */

public interface CamionService extends GenericService<Camion, Long>, RefService<CamionBean> {

	List<CamionBean> getAll();

	List<CamionBean> findByConcessionnaireId(Long id);
}
