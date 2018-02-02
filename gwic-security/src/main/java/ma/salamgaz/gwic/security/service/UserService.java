package ma.salamgaz.gwic.security.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import ma.salamgaz.gwic.admin.model.entity.User;
import ma.salamgaz.gwic.common.service.BaseService;

public interface UserService extends BaseService<User>, UserDetailsService {

    User findUserByUsername(String username);
    
//    UsersListBean list(PageDataBean paginateData);
//
//    UserDetailsBean getUserDetails(Long id);
//
//	List<UserDetailsBean> getAll();

}
