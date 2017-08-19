package com.ayouris.tawassol.repository;

import com.ayouris.tawassol.common.model.entity.PeriodeFacturation;
import com.ayouris.tawassol.common.repository.CommonRepository;
import com.ayouris.tawassol.repository.custom.PeriodeFacturationRepositoryCustom;

public interface PeriodeFacturationRepository extends  CommonRepository<PeriodeFacturation>,PeriodeFacturationRepositoryCustom{
	
}
