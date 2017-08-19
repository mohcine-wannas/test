package com.ayouris.tawassol.security.repository;

import org.springframework.data.repository.NoRepositoryBean;

import com.ayouris.tawassol.admin.model.beans.ResourceBean;
import com.ayouris.tawassol.admin.model.beans.ResultListBean;
import com.ayouris.tawassol.common.model.bean.DataCriteriaBean;

@NoRepositoryBean
public interface CustomResourceRepository {

    ResultListBean<ResourceBean> getRoleResources(Long roleId, DataCriteriaBean criteria);
}
