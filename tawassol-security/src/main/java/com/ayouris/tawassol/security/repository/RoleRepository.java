package com.ayouris.tawassol.security.repository;


import com.ayouris.tawassol.admin.model.entity.Role;
import com.ayouris.tawassol.common.repository.CommonRepository;


public interface RoleRepository extends CommonRepository<Role>, CustomRoleRepository {

}
