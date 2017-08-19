package com.ayouris.tawassol.repository;

import com.ayouris.tawassol.common.model.bean.ControleCamionBean;
import com.ayouris.tawassol.common.model.entity.ControleCamion;
import com.ayouris.tawassol.common.repository.CommonRepository;
import com.ayouris.tawassol.repository.custom.ControleCamionRepositoryCustom;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;

public interface ControleCamionRepository extends  CommonRepository<ControleCamion>,ControleCamionRepositoryCustom, GridRepositoryCustom<ControleCamionBean> {

	
}
