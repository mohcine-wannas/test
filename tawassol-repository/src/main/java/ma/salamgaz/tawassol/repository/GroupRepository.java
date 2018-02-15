package ma.salamgaz.tawassol.repository;

import ma.salamgaz.tawassol.common.model.entity.Group;
import ma.salamgaz.tawassol.common.repository.CommonRepository;
import ma.salamgaz.tawassol.repository.custom.GroupRepositoryCustom;

public interface GroupRepository extends  CommonRepository<Group>,GroupRepositoryCustom {

	
}
