package com.ayouris.tawassol.common.model.bean;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.google.common.reflect.TypeToken;

import lombok.Getter;
import lombok.Setter;
import com.ayouris.tawassol.admin.model.beans.OrganizationInfoBean;
import com.ayouris.tawassol.admin.model.beans.RoleBean;
import com.ayouris.tawassol.admin.model.beans.SiteBean;
import com.ayouris.tawassol.admin.model.entity.Role;
import com.ayouris.tawassol.admin.model.entity.User;
import com.ayouris.tawassol.common.model.entity.Site;
import com.ayouris.tawassol.common.util.RoleInfo;

@Setter
@Getter
@SuppressWarnings("unused")
public class UserDetailsBean {
	
    @SuppressWarnings("serial")
	public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<UserDetailsBean>>() {
    }.getType();
    
	@SuppressWarnings("serial")
	public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<User>>() {
    }.getType();
    

    private Long id;

	private String firstname;

    private String lastname;

    private String username;

    //private OrganizationInfoBean organization;

    private RoleInfo role;

    private Boolean status;

    private String password;

    private String email;

    private String phonenumber;

    private String fax;

    private String mobile;
    
    private Site site;
    

    public static UserDetailsBean convertFull(User user) {
        if (user == null) {
            return null;
        }
        UserDetailsBean u = new UserDetailsBean();
        u.setId(user.getId());
        u.setFirstname(user.getFirstname());
        u.setLastname(user.getLastname());
        u.setUsername(user.getUsername());
        
        if (user.getRoles() != null && !user.getRoles().isEmpty()) {
            Role r = user.getRoles().iterator().next();
            u.setRole(new RoleInfo(r.getName(), r.getRank()));
        }
        
        
        RoleBean rolebean = new RoleBean();
        Set<RoleBean> rolebeans = new HashSet<RoleBean>();
        for (Role role: user.getRoles()){
        	rolebean.setId(role.getId());
        	rolebean.setName(role.getName());
        	rolebean.setDescription(role.getDescription());
        	rolebean.setRank(role.getRank());
        	rolebean.setOrganizationType(role.getOrganizationType());
        	rolebeans.add(rolebean);
        }
        
//        OrganizationInfoBean oi = new OrganizationInfoBean();
//        oi.setCode(user.getOrganization().getAcronym());
//        oi.setId(user.getOrganization().getId());
//        oi.setLabel(user.getOrganization().getName());
//        oi.setRoles(rolebeans);
        
//        u.setOrganization(oi);
        u.setPhonenumber(user.getPhoneNumber());
        u.setFax(user.getFaxNumber());
        u.setEmail(user.getEmail());
        u.setMobile(user.getMobileNumber());
        u.setStatus(Boolean.valueOf(user.isEnabled()));
        
        return u;
    }

}
