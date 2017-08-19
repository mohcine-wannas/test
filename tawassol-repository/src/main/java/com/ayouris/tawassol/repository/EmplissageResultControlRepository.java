package com.ayouris.tawassol.repository;

import com.ayouris.tawassol.common.model.entity.Emplissage;
import com.ayouris.tawassol.common.model.entity.EmplissageResultControl;
import com.ayouris.tawassol.common.repository.CommonRepository;
import com.ayouris.tawassol.repository.custom.EmplissageResultControlRepositoryCustom;

public interface EmplissageResultControlRepository extends CommonRepository<EmplissageResultControl>, EmplissageResultControlRepositoryCustom{

	Long deleteByEmplissage(Emplissage emplissage);

}
