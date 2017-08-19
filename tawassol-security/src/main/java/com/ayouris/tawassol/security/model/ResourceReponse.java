package com.ayouris.tawassol.security.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SuppressWarnings("serial")
public class ResourceReponse implements Serializable {

    private long id;

    private String name;

    private String description;

    List<OperationResponse> operations = new ArrayList<OperationResponse>();

    public void addOperation(OperationResponse operation) {
        operations.add(operation);
    }


    @Override
    public String toString() {
        return "ResourceReponse [id=" + id + ", name=" + name + ", description=" + description + ", operations="
                + operations + "]";
    }

}
