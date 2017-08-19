package com.ayouris.tawassol.repository;

import com.ayouris.tawassol.common.model.bean.FicheMarcheBean;
import com.ayouris.tawassol.common.model.entity.FicheMarche;
import com.ayouris.tawassol.common.repository.CommonRepository;
import com.ayouris.tawassol.repository.custom.FicheMarcheRepositoryCustom;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;

public interface FicheMarcheRepository extends  CommonRepository<FicheMarche>,FicheMarcheRepositoryCustom, GridRepositoryCustom<FicheMarcheBean> {

	
}
