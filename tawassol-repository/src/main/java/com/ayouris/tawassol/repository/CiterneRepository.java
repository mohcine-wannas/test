package com.ayouris.tawassol.repository;

import com.ayouris.tawassol.common.model.bean.CiterneBean;
import com.ayouris.tawassol.common.model.entity.Citerne;
import com.ayouris.tawassol.common.repository.CommonRepository;
import com.ayouris.tawassol.repository.custom.CiterneRepositoryCustom;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;

public interface CiterneRepository extends  CommonRepository<Citerne>,CiterneRepositoryCustom, GridRepositoryCustom<CiterneBean> {

	
}
