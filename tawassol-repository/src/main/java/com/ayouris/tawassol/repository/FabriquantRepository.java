package com.ayouris.tawassol.repository;

import com.ayouris.tawassol.common.model.bean.FabriquantBean;
import com.ayouris.tawassol.common.model.entity.Fabriquant;
import com.ayouris.tawassol.common.repository.CommonRepository;
import com.ayouris.tawassol.repository.custom.FabriquantRepositoryCustom;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;

public interface FabriquantRepository extends  CommonRepository<Fabriquant>,FabriquantRepositoryCustom, GridRepositoryCustom<FabriquantBean> {

	
}
