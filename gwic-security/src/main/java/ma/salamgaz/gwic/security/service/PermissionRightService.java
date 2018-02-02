package ma.salamgaz.gwic.security.service;

import java.util.List;

import ma.salamgaz.gwic.admin.model.entity.PermissionRight;
import ma.salamgaz.gwic.common.service.BaseService;
import ma.salamgaz.gwic.security.model.OrganizationPermissonsResponse;

public interface PermissionRightService extends BaseService<PermissionRight> {

    List<OrganizationPermissonsResponse> getPermissionsTransformed();
}
