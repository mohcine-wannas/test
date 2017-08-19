
package com.ayouris.tawassol.repository;

import com.ayouris.tawassol.common.model.entity.Annee;
import com.ayouris.tawassol.common.repository.CommonRepository;
import com.ayouris.tawassol.repository.custom.AnneeRepositoryCustom;

public interface AnneeRepository extends  CommonRepository<Annee>,AnneeRepositoryCustom{
	public Annee findByLibelleIgnoreCase(String libelle);
}
