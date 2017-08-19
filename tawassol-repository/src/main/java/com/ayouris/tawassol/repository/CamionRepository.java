package com.ayouris.tawassol.repository;

import java.util.List;

import com.ayouris.tawassol.common.model.bean.CamionBean;
import com.ayouris.tawassol.common.model.entity.Camion;
import com.ayouris.tawassol.common.repository.CommonRepository;
import com.ayouris.tawassol.repository.custom.CamionRepositoryCustom;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;

public interface CamionRepository extends  CommonRepository<Camion>,CamionRepositoryCustom, GridRepositoryCustom<CamionBean> {

	List<Camion> findByConcessionnaireIdAndActiveTrue(Long id);

	
}
