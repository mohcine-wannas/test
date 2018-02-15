package com.ayouris.tawassol.service;

import java.util.List;

import com.ayouris.tawassol.common.model.bean.FabriquantBean;
import com.ayouris.tawassol.common.model.entity.Fabriquant;

/**
 * 
 * @author m.wannas
 *
 */

public interface FabriquantService extends GenericService<Fabriquant, Long>, RefService<FabriquantBean> {

	List<FabriquantBean> getAll();
}
