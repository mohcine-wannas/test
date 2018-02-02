package ma.salamgaz.tawassol.security.repository;


import org.springframework.stereotype.Repository;

import ma.salamgaz.tawassol.admin.model.entity.Role;
import ma.salamgaz.tawassol.common.repository.CommonRepository;


public interface RoleRepository extends CommonRepository<Role>, CustomRoleRepository {

}
