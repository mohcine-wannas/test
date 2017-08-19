package com.ayouris.tawassol.repository;

import com.ayouris.tawassol.common.model.bean.TransporteurBean;
import com.ayouris.tawassol.common.model.entity.Transporteur;
import com.ayouris.tawassol.common.repository.CommonRepository;
import com.ayouris.tawassol.repository.custom.TransporteurRepositoryCustom;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;

public interface TransporteurRepository extends  CommonRepository<Transporteur>,TransporteurRepositoryCustom, GridRepositoryCustom<TransporteurBean> {

	
}
