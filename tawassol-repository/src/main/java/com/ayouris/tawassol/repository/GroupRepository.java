package com.ayouris.tawassol.repository;

import com.ayouris.tawassol.common.model.entity.Group;
import com.ayouris.tawassol.common.repository.CommonRepository;
import com.ayouris.tawassol.repository.custom.GroupRepositoryCustom;

public interface GroupRepository extends  CommonRepository<Group>,GroupRepositoryCustom {

	
}
