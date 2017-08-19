package com.ayouris.tawassol.repository;

import com.ayouris.tawassol.common.model.bean.PrixVenteClientBean;
import com.ayouris.tawassol.common.model.entity.PrixVenteClient;
import com.ayouris.tawassol.common.repository.CommonRepository;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;
import com.ayouris.tawassol.repository.custom.PrixVenteClientRepositoryCustom;

public interface PrixVenteClientRepository extends CommonRepository<PrixVenteClient>, PrixVenteClientRepositoryCustom,
		GridRepositoryCustom<PrixVenteClientBean> {

}
