package com.ayouris.tawassol.repository;

import com.ayouris.tawassol.common.model.entity.Fournisseur;
import com.ayouris.tawassol.common.repository.CommonRepository;
import com.ayouris.tawassol.repository.custom.FournisseurRepositoryCustom;

public interface FournisseurRepository extends  CommonRepository<Fournisseur>,FournisseurRepositoryCustom {

	
}
