package com.ayouris.tawassol.repository;

import com.ayouris.tawassol.common.model.bean.DestinationBean;
import com.ayouris.tawassol.common.model.entity.DestinationOL;
import com.ayouris.tawassol.common.repository.CommonRepository;
import com.ayouris.tawassol.repository.custom.DestinationRepositoryCustom;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;

public interface DestinationRepository extends  CommonRepository<DestinationOL>,DestinationRepositoryCustom, GridRepositoryCustom<DestinationBean> {

	
}
