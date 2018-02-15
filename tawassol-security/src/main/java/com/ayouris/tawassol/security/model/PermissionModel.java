package com.ayouris.tawassol.security.model;

import java.util.List;

import com.ayouris.tawassol.common.util.RoleInfo;

public interface PermissionModel {

    Long getId();

    String getOrganization();

    RoleInfo getRole();

    List<ResourceOperationModel> getResources();

}
