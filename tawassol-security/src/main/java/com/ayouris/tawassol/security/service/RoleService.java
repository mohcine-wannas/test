package com.ayouris.tawassol.security.service;

import java.util.List;

import com.ayouris.tawassol.admin.model.beans.ResourceBean;
import com.ayouris.tawassol.admin.model.beans.ResultListBean;
import com.ayouris.tawassol.admin.model.beans.RoleBean;
import com.ayouris.tawassol.common.model.bean.DataCriteriaBean;
import com.ayouris.tawassol.admin.model.entity.Organization;
import com.ayouris.tawassol.admin.model.entity.Role;
import com.ayouris.tawassol.common.service.BaseService;

public interface RoleService extends BaseService<Role> {

	List<RoleBean> getOrganizationRoles(Organization org);

    ResultListBean<RoleBean> getRoles(DataCriteriaBean criteria);

    RoleBean createRole(RoleBean roleBean);

    void updateRole(RoleBean roleBean);

    ResultListBean<ResourceBean> getRoleResources(Long roleId, DataCriteriaBean criteria);
}
