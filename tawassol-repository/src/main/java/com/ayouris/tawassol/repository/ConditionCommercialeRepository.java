package com.ayouris.tawassol.repository;

import com.ayouris.tawassol.common.model.bean.ConditionCommercialeBean;
import com.ayouris.tawassol.common.model.entity.ConditionCommerciale;
import com.ayouris.tawassol.common.repository.CommonRepository;
import com.ayouris.tawassol.repository.custom.ConditionCommercialeRepositoryCustom;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;

public interface ConditionCommercialeRepository extends  CommonRepository<ConditionCommerciale>,ConditionCommercialeRepositoryCustom, GridRepositoryCustom<ConditionCommercialeBean> {

	
}
