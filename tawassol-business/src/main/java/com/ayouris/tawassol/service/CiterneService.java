package com.ayouris.tawassol.service;

import java.util.List;

import com.ayouris.tawassol.common.model.bean.CiterneBean;
import com.ayouris.tawassol.common.model.entity.Citerne;

/**
 * 
 * @author m.wannas
 *
 */

public interface CiterneService extends GenericService<Citerne, Long>, RefService<CiterneBean> {

	List<CiterneBean> getAll();
}
