package ma.salamgaz.gwic.security.repository;


import org.springframework.stereotype.Repository;

import ma.salamgaz.gwic.admin.model.entity.PermissionRight;
import ma.salamgaz.gwic.common.repository.CommonRepository;

@Repository
public interface PermissionRightRepository extends CommonRepository<PermissionRight> {

}
