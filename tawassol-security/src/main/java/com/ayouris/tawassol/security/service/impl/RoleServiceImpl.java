package com.ayouris.tawassol.security.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;

import com.ayouris.tawassol.admin.model.beans.ResourceBean;
import com.ayouris.tawassol.admin.model.beans.ResultListBean;
import com.ayouris.tawassol.admin.model.beans.RoleBean;
import com.ayouris.tawassol.admin.model.entity.Organization;
import com.ayouris.tawassol.admin.model.entity.PermissionRight;
import com.ayouris.tawassol.admin.model.entity.QRole;
import com.ayouris.tawassol.admin.model.entity.Role;
import com.ayouris.tawassol.admin.model.enums.OrganizationType;
import com.ayouris.tawassol.admin.model.enums.PermissionType;
import com.ayouris.tawassol.common.enums.ContactType;
import com.ayouris.tawassol.common.exception.ErrorMessageType;
import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.DataCriteriaBean;
import com.ayouris.tawassol.security.aspect.CheckOrganization;
import com.ayouris.tawassol.security.exception.ServiceSecurityException;
import com.ayouris.tawassol.security.predicate.RolePredicate;
import com.ayouris.tawassol.security.predicate.UserPredicate;
import com.ayouris.tawassol.security.repository.PermissionRightRepository;
import com.ayouris.tawassol.security.repository.ResourceRepository;
import com.ayouris.tawassol.security.repository.RoleRepository;
import com.ayouris.tawassol.security.repository.UserRepository;
import com.ayouris.tawassol.security.service.RoleService;
import com.ayouris.tawassol.security.utils.SecurityUtils;


@Transactional
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private PermissionRightRepository permissionReposotory;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolePredicate rolePredicate;

    @Autowired
    private UserPredicate userPredicate;

    @Autowired
    private CustomModelMapper mapper;

    @Override
    @CheckOrganization(name = { ContactType.SIEGE })
    public List<Role> findAll() {
        try {
            return roleRepository.findAll();

        } catch (DataAccessException e) {
        	LOGGER.error(e.getMessage(), e);
            throw new ServiceSecurityException(ErrorMessageType.TMP_EXCEPTION, e);
        }
    }

    @Override
    @CheckOrganization(name = { ContactType.SIEGE })
    public Role save(Role role) {
        try {
            return roleRepository.saveAndFlush(role);

        } catch (DataAccessException e) {
        	LOGGER.error(e.getMessage(), e);
            throw new ServiceSecurityException(ErrorMessageType.TMP_EXCEPTION, e);
        }
    }

    @Override
    @CheckOrganization(name = { ContactType.SIEGE })
    public Role findOne(Long id) {
        try {
            QRole role = QRole.role;
            Predicate pr = role.id.eq(id);
            return roleRepository.findOne(pr);
        } catch (DataAccessException e) {
        	LOGGER.error(e.getMessage(), e);
            throw new ServiceSecurityException(ErrorMessageType.TMP_EXCEPTION, e);
        }
    }

    @Override
    @CheckOrganization(name = { ContactType.SIEGE })
    public Role update(Role role) {
        try {
            return roleRepository.saveAndFlush(role);
        } catch (DataAccessException e) {
        	LOGGER.error(e.getMessage(), e);
            throw new ServiceSecurityException(ErrorMessageType.TMP_EXCEPTION, e);
        }
    }

    @Override
    @CheckOrganization(name = { ContactType.SIEGE })
    public void delete(Long id) {
        try {
            roleRepository.delete(id);
        } catch (DataAccessException e) {
        	LOGGER.error(e.getMessage(), e);
            throw new ServiceSecurityException(ErrorMessageType.TMP_EXCEPTION, e);
        }
    }
    
    
   //------------------------------------------------------------------------------------------
    
    @Override
    public List<RoleBean> getOrganizationRoles(Organization org) {

        OrganizationType organizationType = getOrganizationType(org);
        Iterable<Role> roles = roleRepository.findAll(rolePredicate.matchByOrganizationType(organizationType));
        List<RoleBean> rolesBeans = mapper.mapStrict(roles, RoleBean.LIST_TYPE);

        return rolesBeans;
    }

    @Override
    public ResultListBean<RoleBean> getRoles(DataCriteriaBean criteria) {
        //return roleRepository.getRolesByCriteria(criteria);
    	return null;
    }

    @Override
    public ResultListBean<ResourceBean> getRoleResources(Long roleId, DataCriteriaBean criteria) {
        //return resourceRepository.getRoleResources(roleId, criteria);
    	return null;
    }

    @Override
    @Transactional
    public RoleBean createRole(RoleBean roleBean) {
        checkCreationRole(roleBean);
        Role roleEntity = (Role) mapper.mapStrict(roleBean, Role.class);

        // In creation initialise update date with creation date
        Date updatedate = new Date();
        roleEntity.setUpdatedOn(updatedate);

        // Update bean information
        roleBean.setId(roleRepository.save(roleEntity).getId());
        roleBean.setUpdatedOn(updatedate);

        return roleBean;
    }

    @Override
    @Transactional
    public void updateRole(RoleBean role) {

        checkUpdateRole(role);

        // Update role
        Role roleToUpdate = (Role) mapper.mapStrict(role, Role.class);

        // Update permissions
        roleToUpdate.setPermissions(buildPermissions(role.getResources()));

        roleRepository.save(roleToUpdate);
    }

    /**
     * Check for compliance with all business rules. (creation Case)
     * @param role  role bean to create
     */
    private void checkCreationRole(RoleBean roleBean) {

        // Check rank
        Set<Role> roles = SecurityUtils.getCurrentUser().getRoles();
        // A user can only have one role
        int userRank = roles.iterator().next().getRank();
        if (userRank > roleBean.getRank()) {
            LOGGER.error("Role rank must be greater or equal than user rank");
            throw new ServiceSecurityException(ErrorMessageType.ROLE_RANK_NOT_VALID);
        }

        // Check unique role name
        long roleNameNumber = roleRepository.count(rolePredicate.matchByName(roleBean));
        if (roleNameNumber >= 1) {
            LOGGER.error("Role name already exists");
            throw new ServiceSecurityException(ErrorMessageType.ROLE_NAME_ALREADY_EXISTS);
        }
    }

    /**
     * Check for compliance with all business rules. Check performed only when update
     * @param role role bean to update
     */
    private void checkUpdateRole(RoleBean roleBean) {

        checkCreationRole(roleBean);

        /* Check if the given role can be disabled (Don't already used). */
        if (!roleBean.isEnabled()) {
            long users = userRepository.count(userPredicate.userRole(roleBean.getId()));
            if (users >= 1) {
                LOGGER.error("The given role can't be disabled, because it's already used");
                throw new ServiceSecurityException(ErrorMessageType.ROLE_ALREADY_USED);
            }
        }
        
        /* Check if the organization type has changed. */
        long role = roleRepository.count(rolePredicate.matchByOrganizationTypeRoleId(roleBean));
        if (role == 0) {
            LOGGER.error("The organization type can't be changed");
            throw new ServiceSecurityException(ErrorMessageType.ROLE_ORGANIZATION_TYPE);
        }
    }

    
    private Set<PermissionRight> buildPermissions(List<ResourceBean> permissions) {

        /*  Build map with key composed of the resource and operation name */
        List<PermissionRight> permissionsRight = permissionReposotory.findAll();
        Map<String, PermissionRight> permissionsMap = new HashMap<>();
        String permissionRightKey;
        for (PermissionRight pr : permissionsRight) {
            permissionRightKey = pr.getResource().getName() + pr.getOperation().getName();
            permissionsMap.put(permissionRightKey.toLowerCase(), pr);
        }

        /* Build list of new permissions right */
        Set<PermissionRight> newPermissions = new HashSet<>();
        if (CollectionUtils.isNotEmpty(permissions)) {
            for (ResourceBean perm : permissions) {
                if (!PermissionType.NONE.equals(perm.getPermissionType())) {
                    newPermissions.add(permissionsMap.get((perm.getName() + perm.getPermissionType()).toLowerCase()));
                    if (PermissionType.WRITE.equals(perm.getPermissionType())) {
                        newPermissions.add(permissionsMap.get((perm.getName() + PermissionType.READ).toLowerCase()));
                    }
                }
            }
        }
        return newPermissions;
    }

    /**
     * Get organization type from organization instance.
     * @param orga User organization
     * @return organization type
     */
    private OrganizationType getOrganizationType(Organization orga) {
        OrganizationType organizationType = null;
        return organizationType;
    }

}
