package com.ayouris.tawassol.security.service;

import java.util.List;

import com.ayouris.tawassol.admin.model.entity.PermissionRight;
import com.ayouris.tawassol.common.service.BaseService;
import com.ayouris.tawassol.security.model.OrganizationPermissonsResponse;

public interface PermissionRightService extends BaseService<PermissionRight> {

    List<OrganizationPermissonsResponse> getPermissionsTransformed();
}
