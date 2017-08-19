package com.ayouris.tawassol.repository;

import com.ayouris.tawassol.common.model.bean.PrixVenteConcessionnaireBean;
import com.ayouris.tawassol.common.model.entity.PrixVenteConcessionnaire;
import com.ayouris.tawassol.common.repository.CommonRepository;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;
import com.ayouris.tawassol.repository.custom.PrixVenteConcessionnaireRepositoryCustom;

public interface PrixVenteConcessionnaireRepository extends CommonRepository<PrixVenteConcessionnaire>, PrixVenteConcessionnaireRepositoryCustom,
		GridRepositoryCustom<PrixVenteConcessionnaireBean> {

}
