package ma.salamgaz.gwic.repository;

import ma.salamgaz.gwic.common.model.entity.Group;
import ma.salamgaz.gwic.common.repository.CommonRepository;
import ma.salamgaz.gwic.repository.custom.GroupRepositoryCustom;

public interface GroupRepository extends  CommonRepository<Group>,GroupRepositoryCustom {

	
}
