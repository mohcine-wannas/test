package com.ayouris.tawassol.repository;

import com.ayouris.tawassol.common.model.bean.MarqueBean;
import com.ayouris.tawassol.common.model.entity.Marque;
import com.ayouris.tawassol.common.repository.CommonRepository;
import com.ayouris.tawassol.repository.custom.MarqueRepositoryCustom;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;

public interface MarqueRepository extends  CommonRepository<Marque>,MarqueRepositoryCustom, GridRepositoryCustom<MarqueBean> {

	
}
