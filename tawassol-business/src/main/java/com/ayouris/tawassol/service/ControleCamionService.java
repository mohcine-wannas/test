package com.ayouris.tawassol.service;

import java.util.List;

import com.ayouris.tawassol.common.model.bean.ControleCamionBean;
import com.ayouris.tawassol.common.model.entity.ControleCamion;

/**
 * 
 * @author m.wannas
 *
 */

public interface ControleCamionService extends GenericService<ControleCamion, Long>, RefService<ControleCamionBean> {

	List<ControleCamionBean> getAll();
}
