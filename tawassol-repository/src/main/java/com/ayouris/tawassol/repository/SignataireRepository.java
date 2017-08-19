package com.ayouris.tawassol.repository;

import java.util.List;

import com.ayouris.tawassol.common.model.bean.SignataireBean;
import com.ayouris.tawassol.common.model.entity.Signataire;
import com.ayouris.tawassol.common.repository.CommonRepository;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;
import com.ayouris.tawassol.repository.custom.SignataireRepositoryCustom;

public interface SignataireRepository extends  CommonRepository<Signataire>,SignataireRepositoryCustom, GridRepositoryCustom<SignataireBean> {

	List<Signataire> findByConcessionnaireId(Long id);

	
}
