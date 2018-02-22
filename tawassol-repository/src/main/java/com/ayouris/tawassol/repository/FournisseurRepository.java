package com.ayouris.tawassol.repository;

import com.ayouris.tawassol.common.model.bean.FournisseurBean;
import com.ayouris.tawassol.common.model.entity.Fournisseur;
import com.ayouris.tawassol.common.repository.CommonRepository;
import com.ayouris.tawassol.repository.custom.FournisseurRepositoryCustom;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;

public interface FournisseurRepository extends  CommonRepository<Fournisseur>,FournisseurRepositoryCustom, GridRepositoryCustom<FournisseurBean> {

	
}
