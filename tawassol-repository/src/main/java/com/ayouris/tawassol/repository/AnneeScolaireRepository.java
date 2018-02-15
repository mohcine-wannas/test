package com.ayouris.tawassol.repository;

import java.util.List;

import com.ayouris.tawassol.common.model.entity.AnneeScolaire;
import com.ayouris.tawassol.common.repository.CommonRepository;

public interface AnneeScolaireRepository extends  CommonRepository<AnneeScolaire> {

	List<AnneeScolaire> findByCurrentTrue();

	
}
