package com.ayouris.tawassol.repository;

import java.util.List;

import com.ayouris.tawassol.common.model.entity.GroupeAppellation;
import com.ayouris.tawassol.common.repository.CommonRepository;

public interface GroupeAppellationRepository extends  CommonRepository<GroupeAppellation> {

	List<GroupeAppellation> findByCycleIdAndActiveTrueOrderByOrderAsc(Long id);
	
}
