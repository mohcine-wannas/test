package ma.salamgaz.gwic.security.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ma.salamgaz.gwic.admin.model.entity.Organization;
import ma.salamgaz.gwic.common.util.RoleInfo;
import ma.salamgaz.gwic.security.model.PermissionModel;
import ma.salamgaz.gwic.security.model.ResourceOperationModel;

import lombok.Getter;

@Getter
@SuppressWarnings("serial")
class PermissionModelImpl implements Serializable, PermissionModel {

    private final Long id = 0l;

    private final String organization = "";

    private final RoleInfo role = new RoleInfo();

    private final List<ResourceOperationModel> resources = new ArrayList<ResourceOperationModel>();
    
    PermissionModelImpl() {
    	
    }

    PermissionModelImpl(Long id, String organization, RoleInfo role, List<ResourceOperationModel> resources) {
//        this.id = id;
//        this.organization = organization;
//        this.role = role;
//        this.resources = resources;
    }

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getOrganization() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoleInfo getRole() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ResourceOperationModel> getResources() {
		// TODO Auto-generated method stub
		return null;
	}

}
