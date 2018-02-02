package ma.salamgaz.gwic.security.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SuppressWarnings("serial")
public class UserContextResponse implements Serializable {

    private long id;
    private final String username;
    private final String firstname;
    private final String lastname;
    private final MemberDetails details;
    private final PermissionModel permissionModel;

    public UserContextResponse(long id, String username, String firstname, String lastname, MemberDetails details,
            PermissionModel permissionModel) {
        this.id = id;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.details = details;
        this.permissionModel = permissionModel;
    }

}