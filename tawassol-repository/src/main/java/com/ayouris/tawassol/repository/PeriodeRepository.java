package com.ayouris.tawassol.repository;

import java.util.List;

import com.ayouris.tawassol.common.model.bean.PeriodeBean;
import com.ayouris.tawassol.common.model.entity.Periode;
import com.ayouris.tawassol.common.repository.CommonRepository;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;
import com.ayouris.tawassol.repository.custom.PeriodeRepositoryCustom;

public interface PeriodeRepository extends  CommonRepository<Periode>,PeriodeRepositoryCustom, GridRepositoryCustom<PeriodeBean> {

	List<Periode> removeByConditionCommercialeId(Long id);
	List<Periode> findByConditionCommercialeId(Long id);


	
}
