package com.ayouris.tawassol.service;

import java.util.List;

import com.ayouris.tawassol.common.model.bean.MarqueBean;
import com.ayouris.tawassol.common.model.entity.Marque;

/**
 * 
 * @author m.wannas
 *
 */

public interface MarqueService extends GenericService<Marque, Long>, RefService<MarqueBean> {

	List<MarqueBean> getAll();
}
