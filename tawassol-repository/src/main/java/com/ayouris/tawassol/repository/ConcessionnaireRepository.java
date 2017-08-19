package com.ayouris.tawassol.repository;

import com.ayouris.tawassol.common.model.entity.Concessionnaire;
import com.ayouris.tawassol.common.repository.CommonRepository;
import com.ayouris.tawassol.repository.custom.ConcessionnaireRepositoryCustom;

public interface ConcessionnaireRepository extends  CommonRepository<Concessionnaire>,ConcessionnaireRepositoryCustom {

	
}
