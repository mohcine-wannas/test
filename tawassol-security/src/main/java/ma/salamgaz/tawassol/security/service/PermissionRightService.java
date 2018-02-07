package ma.salamgaz.tawassol.security.service;

import java.util.List;

import ma.salamgaz.tawassol.admin.model.entity.PermissionRight;
import ma.salamgaz.tawassol.common.service.BaseService;
import ma.salamgaz.tawassol.security.model.OrganizationPermissonsResponse;

public interface PermissionRightService extends BaseService<PermissionRight> {

    List<OrganizationPermissonsResponse> getPermissionsTransformed();
}
