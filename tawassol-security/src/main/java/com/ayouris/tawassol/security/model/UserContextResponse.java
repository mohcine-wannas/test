package com.ayouris.tawassol.security.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import com.ayouris.tawassol.common.model.bean.SchoolBean;

@Getter
@Setter
@SuppressWarnings("serial")
public class UserContextResponse implements Serializable {

    private long id;
    private final String username;
    private final String firstname;
    private final String lastname;
    private final SchoolDetails school;
    private final MemberDetails details;
    private final PermissionModel permissionModel;

    public UserContextResponse(long id, String username, String firstname, String lastname, MemberDetails details, SchoolDetails school,
            PermissionModel permissionModel) {
        this.id = id;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.details = details;
        this.school = school;
        this.permissionModel = permissionModel;
    }

}