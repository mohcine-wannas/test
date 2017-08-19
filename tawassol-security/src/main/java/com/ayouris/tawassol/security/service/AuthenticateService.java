package com.ayouris.tawassol.security.service;

import com.ayouris.tawassol.common.model.bean.UserDataAuthenticate;

import java.net.UnknownHostException;

import javax.servlet.http.HttpServletResponse;

import com.ayouris.tawassol.admin.model.entity.User;
import com.ayouris.tawassol.security.exception.AuthorizationException;
import com.ayouris.tawassol.security.model.UserData;
import com.ayouris.tawassol.security.utils.UserAuthentication;

public interface AuthenticateService {

    UserData authenticateForm(String username, String password);

    UserData authenticate(UserDataAuthenticate user);

    UserData authenticateToken(String token);

    User checkToken(String token, String ipAddres) throws AuthorizationException;

    User findUserByUsername(String username);

    UserData authenticateRemoteUser(String username);

    UserData logout();
    
    void addAuthentication(HttpServletResponse response, UserAuthentication authentication) throws UnknownHostException;
}
