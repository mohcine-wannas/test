package ma.salamgaz.tawassol.admin.model.beans;

import java.io.Serializable;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;
import lombok.Setter;

import ma.salamgaz.tawassol.admin.model.enums.PermissionType;


@Setter
@Getter
public class ResourceBean implements Serializable {

    private static final long serialVersionUID = 6850103854409667574L;

    private Long id;

    private String name;

    private String description;

    private PermissionType permissionType;

    public ResourceBean() {
        permissionType = PermissionType.NONE;
    }

    @QueryProjection
    public ResourceBean(Long id, String name, String description, Long operationsNumber) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.permissionType = PermissionType.calculatePermissionType(operationsNumber);
    }


}
