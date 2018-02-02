package ma.salamgaz.tawassol.security.model;

import java.util.List;

import ma.salamgaz.tawassol.common.util.RoleInfo;

public interface PermissionModel {

    Long getId();

    String getOrganization();

    RoleInfo getRole();

    List<ResourceOperationModel> getResources();

}
