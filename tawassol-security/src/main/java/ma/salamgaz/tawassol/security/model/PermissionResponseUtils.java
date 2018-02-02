package ma.salamgaz.tawassol.security.model;

import java.util.ArrayList;
import java.util.List;

import ma.salamgaz.tawassol.admin.model.entity.PermissionRight;

public class PermissionResponseUtils {

    public static List<OrganizationPermissonsResponse> transformePermissions(List<PermissionRight> permissions) {
        List<OrganizationPermissonsResponse> retour = new ArrayList<OrganizationPermissonsResponse>();
        OrganizationPermissonsResponse item = new OrganizationPermissonsResponse();
        item.getResources().add(getResource(permissions));
        retour.add(item);
        return retour;
    }

    private static ResourceReponse getResource(List<PermissionRight> permissions) {
        ResourceReponse resource = new ResourceReponse();
        OperationResponse operation = null;
        for (PermissionRight pr : permissions) {
            operation = new OperationResponse();
            operation.setId(pr.getOperation().getId());
            operation.setName(pr.getOperation().getName());
            operation.setDescription(pr.getOperation().getDescription());
            operation.setPermissionId(pr.getId());
            resource.addOperation(operation);
            if (resource.getName() == null || resource.getName().isEmpty() || "".equals(resource.getName())) {
                resource.setName(pr.getResource().getName());
                resource.setId(pr.getResource().getId());
                resource.setDescription(pr.getResource().getDescription());
            }
        }
        return resource;
    }

}
