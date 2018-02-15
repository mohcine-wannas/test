package com.ayouris.tawassol.admin.model.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;

import com.ayouris.tawassol.admin.model.enums.OrganizationType;
import com.ayouris.tawassol.common.model.entity.generic.AuditEntity;
import com.ayouris.tawassol.common.mapper.NotNullable;

import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Setter
@Entity
@Table(name = "role", schema = "tawassol")
@NotNullable
public class Role extends AuditEntity implements GrantedAuthority {

    private static final long serialVersionUID = -6740570273674810363L;

    private String name;
    private String description;
    private Integer rank;
    private OrganizationType organizationType;
    private boolean enabled;

    private Set<PermissionRight> permissions = new HashSet<PermissionRight>(0);

    @Column(name = "name", length = 128, nullable = false, unique = true)
    public String getName() {
        return this.name;
    }

    @Column(name = "description", nullable = false)
    public String getDescription() {
        return description;
    }


    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_permission", schema = "tawassol", joinColumns = {
            @JoinColumn(name = "role_id", nullable = false, updatable = false) }, inverseJoinColumns = {
                    @JoinColumn(name = "permission_id", nullable = false, updatable = false, foreignKey = @ForeignKey(name = "FK_ROLE_PR_PR_ID")) })
    public Set<PermissionRight> getPermissions() {
        return this.permissions;
    }

    @Column(name = "rank", nullable = false)
    public Integer getRank() {
        return rank;
    }

    @Column(name = "organization_type", length = 128, nullable = false)
    @Enumerated(EnumType.STRING)
    public OrganizationType getOrganizationType() {
        return organizationType;
    }

    @Column(name = "enabled", nullable = false)
    public boolean isEnabled() {
        return enabled;
    }

    @Transient
    @Override
    @JsonIgnore
    public String getAuthority() {
        return name;
    }
}
