package com.ayouris.tawassol.admin.model.beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;



import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrganizationInfoBean implements Serializable {

    private static final long serialVersionUID = 1925976094569943688L;

    private Long id;

    private String label;

    private String code;
    
    private String name;

    private Set<RoleBean> roles;

    public Set<RoleBean> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleBean> roles) {
        this.roles = new HashSet<RoleBean>();
        for (RoleBean role : roles) {
            if (role.isEnabled()) {
                this.roles.add(role);
            }
        }
    }
 
}
