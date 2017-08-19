package com.ayouris.tawassol.security.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SuppressWarnings("serial")
public class OperationResponse implements Serializable {

    private long id;

    private long permissionId;

    private String name;

    private String description;

    @Override
    public String toString() {
        return "OperationResponse [id=" + id + ", permissionId=" + permissionId + ", name=" + name + ", description="
                + description + "]";
    }

}
