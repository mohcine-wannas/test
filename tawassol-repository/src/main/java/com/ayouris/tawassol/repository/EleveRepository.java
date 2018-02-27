package com.ayouris.tawassol.repository;

import java.util.List;

import com.ayouris.tawassol.common.model.entity.Eleve;
import com.ayouris.tawassol.common.repository.CommonRepository;

public interface EleveRepository extends  CommonRepository<Eleve> {

	List<Eleve> findByCodeMassarAndEnabledTrue(String codeMassar);

}
