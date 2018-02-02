package ma.salamgaz.gwic.security.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SuppressWarnings("serial")
public class OrganizationRoleResponse implements Serializable {

    private String organizationType;

    private List<RoleResponse> roles = new ArrayList<RoleResponse>();

    public void addRole(RoleResponse role) {
        roles.add(role);
    }

    @Override
    public String toString() {
        return "OrganizationRoleResponse [organizationType=" + organizationType + ", roles=" + roles + "]";
    }

}
