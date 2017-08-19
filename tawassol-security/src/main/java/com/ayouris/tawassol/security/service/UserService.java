package com.ayouris.tawassol.security.service;

import java.util.List;

import com.ayouris.tawassol.admin.model.beans.UserBean;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.ayouris.tawassol.admin.model.entity.User;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.bean.UserDetailsBean;
import com.ayouris.tawassol.common.model.bean.UsersListBean;
import com.ayouris.tawassol.common.service.BaseService;

public interface UserService extends BaseService<User>, UserDetailsService {

    User findUserByUsername(String username);
    
    UsersListBean list(PageDataBean paginateData);

    UserDetailsBean getUserDetails(Long id);

	List<UserDetailsBean> getAll();

	List<UserDetailsBean> getBySite(Long userId);

}
