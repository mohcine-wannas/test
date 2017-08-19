package com.ayouris.tawassol.admin.model.entity;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Setter;
import com.ayouris.tawassol.common.mapper.NotNullable;
import com.ayouris.tawassol.common.model.entity.generic.BaseEntity;

/**
 * PermissionRight generated by hbm2java
 */
@Setter
@Entity
@Table(name = "permission_right", schema = "tawassol")
@NotNullable
public class PermissionRight extends BaseEntity {

    private static final long serialVersionUID = -3664533127190808915L;

    private Operation operation;
    private Resource resource;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "operation_id", nullable = false, foreignKey = @ForeignKey(name = "FK_PERMISSION_RIGHT_OP_ID"))
   // @ForeignKey(name="FK_PERMISSION_RIGHT_OP_ID")
    public Operation getOperation() {
        return this.operation;
    }

   
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "resource_id", nullable = false, foreignKey = @ForeignKey(name = "FK_PR_RESOURCE_ID"))
    public Resource getResource() {
        return this.resource;
    }

}
