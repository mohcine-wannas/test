package com.ayouris.tawassol.security.service;

import java.util.List;

import com.ayouris.tawassol.common.model.bean.CycleBean;
import com.ayouris.tawassol.common.model.entity.Cycle;
import com.ayouris.tawassol.common.service.BaseService;

/**
 * 
 * @author m.wannas
 *
 */

public interface CycleSecurityService extends BaseService<Cycle> {

	List<CycleBean> getAll();

}
