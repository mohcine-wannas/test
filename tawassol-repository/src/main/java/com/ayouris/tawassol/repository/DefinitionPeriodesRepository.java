package com.ayouris.tawassol.repository;

import com.ayouris.tawassol.common.model.entity.DefinitionPeriodes;
import com.ayouris.tawassol.common.repository.CommonRepository;
import com.ayouris.tawassol.repository.custom.DefinitionPeriodesRepositoryCustom;

public interface DefinitionPeriodesRepository extends CommonRepository<DefinitionPeriodes> ,
        DefinitionPeriodesRepositoryCustom{
}
