package ma.salamgaz.gwic.security.repository;


import org.springframework.stereotype.Repository;

import ma.salamgaz.gwic.admin.model.entity.Role;
import ma.salamgaz.gwic.common.repository.CommonRepository;


public interface RoleRepository extends CommonRepository<Role>, CustomRoleRepository {

}
