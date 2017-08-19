package com.ayouris.tawassol.admin.model.beans;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.common.reflect.TypeToken;
import com.ayouris.tawassol.admin.model.enums.OrganizationType;
import com.ayouris.tawassol.common.view.RequestView.CreationRoleView;
import com.ayouris.tawassol.common.view.RequestView.ListRoleView;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

@Setter
@Getter
public class RoleBean implements Serializable {

    private static final long serialVersionUID = -7419089138964718133L;

    @SuppressWarnings("serial")
	public static final Type LIST_TYPE = new TypeToken<ArrayList<RoleBean>>() {
    }.getType();

    @JsonView({ ListRoleView.class, CreationRoleView.class })
    private Long id;

    @JsonView({ ListRoleView.class, CreationRoleView.class })
    @JsonProperty("label")
    private String name;

    @JsonView({ ListRoleView.class, CreationRoleView.class })
    private String description;

    @JsonView({ ListRoleView.class, CreationRoleView.class })
    private Integer rank;

    @JsonView({ ListRoleView.class, CreationRoleView.class })
    private OrganizationType organizationType;

    @JsonView({ ListRoleView.class, CreationRoleView.class })
    private Date updatedOn;

    @JsonView({ ListRoleView.class, CreationRoleView.class })
    private boolean enabled;

    @JsonView(ListRoleView.class)
    private boolean updatable;

    private List<ResourceBean> resources;


    public boolean isEnabled() {
        return enabled;
    }

    public boolean isUpdatable() {
        return updatable;
    }

}
