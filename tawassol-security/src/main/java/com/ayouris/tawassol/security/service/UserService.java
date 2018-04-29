package com.ayouris.tawassol.security.service;

import com.ayouris.tawassol.admin.model.entity.User;
import com.ayouris.tawassol.common.service.BaseService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends BaseService<User>, UserDetailsService {

    User findUserByUsername(String username);

    void setFCMToken(Long id, String token, Boolean isMobile);

    void clearFCMToken(Long id, Boolean isMobile);

}
