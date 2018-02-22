package com.ayouris.tawassol.security.service;

import java.util.Map;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ayouris.tawassol.admin.model.entity.User;
import com.ayouris.tawassol.common.service.BaseService;

public interface UserService extends BaseService<User>, UserDetailsService {

    User findUserByUsername(String username);


}
