
package com.ayouris.tawassol.repository;

import com.ayouris.tawassol.common.model.entity.Mois;
import com.ayouris.tawassol.common.repository.CommonRepository;
import com.ayouris.tawassol.repository.custom.MoisRepositoryCustom;

public interface MoisRepository extends  CommonRepository<Mois>,MoisRepositoryCustom{
	public Mois findByLibelleIgnoreCase(String libelle);
	
}
