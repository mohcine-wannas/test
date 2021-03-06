package com.ayouris.tawassol.security.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ayouris.tawassol.admin.model.entity.PermissionRight;
import com.ayouris.tawassol.admin.model.entity.Role;
import com.ayouris.tawassol.common.util.RoleInfo;
import com.ayouris.tawassol.security.model.PermissionModel;
import com.ayouris.tawassol.security.model.ResourceOperationModel;

public final class PermissionUtils {

    public static PermissionModel createPermissionModel(Long aUserId, String organization, Set<Role> aRoles) {
        return createPermissionModel(aUserId, organization, aRoles, null, null);
    }

    public static PermissionModel createPermissionModel(Long aUserId, String organization, Set<Role> aRoles,
             Boolean importer, Boolean exporter) {
        if (aRoles == null || aRoles.isEmpty()) {
            return null;
        }
        Map<String, Set<String>> resources = new HashMap<String, Set<String>>();
        RoleInfo ri = null;
        for (Role role : aRoles) {
            ri = new RoleInfo(role.getName(), role.getRank());
            if (role.getPermissions() != null && !role.getPermissions().isEmpty()) {
                for (PermissionRight permissionRight : role.getPermissions()) {
                    // Create Map <Role, List<ResourceOperation>>
                    if (resources.containsKey(permissionRight.getResource().getName())) {
                        resources.get(permissionRight.getResource().getName()).add(
                                permissionRight.getOperation().getName());
                    } else {
                        Set<String> operations = new HashSet<String>();
                        operations.add(permissionRight.getOperation().getName());
                        resources.put(permissionRight.getResource().getName(), operations);
                    }
                }
            }
        }

        List<ResourceOperationModel> resourcesModel = createResources(resources);
        PermissionModel model = new PermissionModelImpl(aUserId, organization, ri, resourcesModel);
        return model;

    }

    private static List<ResourceOperationModel> createResources(Map<String, Set<String>> resources) {
        List<ResourceOperationModel> retour = new ArrayList<ResourceOperationModel>();
        ResourceOperationModel item = null;
        for (Iterator<String> it = resources.keySet().iterator(); it.hasNext();) {
            String resourceName = it.next();
            item = new ResourceOperationModel();
            item.setResource(resourceName);
            item.setOperation(resources.get(resourceName));
            retour.add(item);
        }
        return retour;
    }
}
