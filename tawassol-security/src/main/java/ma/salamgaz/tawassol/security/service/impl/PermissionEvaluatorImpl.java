package ma.salamgaz.tawassol.security.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ma.salamgaz.tawassol.common.enums.ContactType;
import ma.salamgaz.tawassol.common.enums.OperationType;
import ma.salamgaz.tawassol.security.aspect.CheckResourceOperations;
import ma.salamgaz.tawassol.security.model.PermissionModel;
import ma.salamgaz.tawassol.security.model.ResourceOperationModel;
import ma.salamgaz.tawassol.security.service.PermissionEvaluator;
import ma.salamgaz.tawassol.security.utils.SecurityUtils;

@Service("permissionEvaluatorService")
public class PermissionEvaluatorImpl implements PermissionEvaluator {

    @Override
    public boolean hasOrganization(ContactType organizationType) {
        PermissionModel model = SecurityUtils.getCurrentUserPermissionModel();
        if (model.getOrganization().equalsIgnoreCase(organizationType.name())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean hasAnyOrganization(ContactType[] organizationsType) {
        PermissionModel model = SecurityUtils.getCurrentUserPermissionModel();
        for (ContactType organizationType : organizationsType) {
            if (model.getOrganization().equalsIgnoreCase(organizationType.name())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasResource(String resource) {
        PermissionModel model = SecurityUtils.getCurrentUserPermissionModel();
        for (ResourceOperationModel resourceModel : model.getResources()) {
            if (resourceModel.getResource().equalsIgnoreCase(resource)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean hasAnyResource(String[] resources) {
        PermissionModel model = SecurityUtils.getCurrentUserPermissionModel();
        for (ResourceOperationModel resourceModel : model.getResources()) {
            for (String resource : resources) {
                if (resourceModel.getResource().equalsIgnoreCase(resource)) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean hasResourceAndAnyOperation(CheckResourceOperations checkedResourceOperations) {
        PermissionModel model = SecurityUtils.getCurrentUserPermissionModel();
        for (ResourceOperationModel resourceModel : model.getResources()) {
            if (resourceModel.getResource().equalsIgnoreCase(checkedResourceOperations.resource())) {

                for (OperationType operationType : checkedResourceOperations.operations()) {
                    if (resourceModel.getOperation().contains(operationType.name())) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    @Override
    public boolean hasRole(String role) {
        return SecurityUtils.hasRole(role);
    }

    @Override
    public boolean hasAnyRole(String[] roles) {
        return SecurityUtils.hasAnyRole(roles);
    }


}
