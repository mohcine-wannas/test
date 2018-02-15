package ma.salamgaz.tawassol.security.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import ma.salamgaz.tawassol.admin.model.entity.User;
import ma.salamgaz.tawassol.common.service.BaseService;

public interface UserService extends BaseService<User>, UserDetailsService {

    User findUserByUsername(String username);
    
//    UsersListBean list(PageDataBean paginateData);
//
//    UserDetailsBean getUserDetails(Long id);
//
//	List<UserDetailsBean> getAll();

}
