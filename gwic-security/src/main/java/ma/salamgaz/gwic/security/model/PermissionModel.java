package ma.salamgaz.gwic.security.model;

import java.util.List;

import ma.salamgaz.gwic.common.util.RoleInfo;

public interface PermissionModel {

    Long getId();

    String getOrganization();

    RoleInfo getRole();

    List<ResourceOperationModel> getResources();

}
