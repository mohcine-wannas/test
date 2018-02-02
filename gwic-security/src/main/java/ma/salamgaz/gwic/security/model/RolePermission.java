package ma.salamgaz.gwic.security.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SuppressWarnings("serial")
public class RolePermission implements Serializable{

	private String roleName;
	
	private List<ResourceOperationModel> resourceOperationModels = new ArrayList<ResourceOperationModel>();
}
