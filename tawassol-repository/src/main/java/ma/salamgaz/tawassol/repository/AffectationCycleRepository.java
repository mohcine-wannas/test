package ma.salamgaz.tawassol.repository;

import java.util.List;

import org.springframework.context.annotation.Primary;

import ma.salamgaz.tawassol.common.model.entity.AffectationCycle;
import ma.salamgaz.tawassol.common.repository.CommonRepository;

@Primary
public interface AffectationCycleRepository extends  CommonRepository<AffectationCycle> {

	List<AffectationCycle> findBySchoolIdAndAnneeScolaireId(Long schoolId, Long anneeScolaireId);
	
}
