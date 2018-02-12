package ma.salamgaz.tawassol.repository;

import java.util.List;

import ma.salamgaz.tawassol.common.model.entity.AnneeScolaire;
import ma.salamgaz.tawassol.common.repository.CommonRepository;

public interface AnneeScolaireRepository extends  CommonRepository<AnneeScolaire> {

	List<AnneeScolaire> findByCurrentTrue();

	
}
