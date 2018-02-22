package com.ayouris.tawassol.repository;

import com.ayouris.tawassol.common.model.bean.PrestationBean;
import com.ayouris.tawassol.common.model.entity.Prestation;
import com.ayouris.tawassol.common.repository.CommonRepository;
import com.ayouris.tawassol.repository.custom.PrestationRepositoryCustom;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;

public interface PrestationRepository extends  CommonRepository<Prestation>,PrestationRepositoryCustom, GridRepositoryCustom<PrestationBean> {

	
}
