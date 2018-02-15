package com.ayouris.tawassol.security.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;

import com.ayouris.tawassol.admin.model.entity.User;
import com.ayouris.tawassol.security.exception.AuthorizationException;
import com.ayouris.tawassol.security.model.UserData;

public interface AuthenticateService {

    UserData authenticateForm(String username, String password, HttpServletRequest request);

    UserData authenticateToken(String token);

    User checkToken(String token, String ipAddres) throws AuthorizationException;

    User findUserByUsername(String username);

    UserData authenticateRemoteUser(String username);

    UserData logout();
    
    Authentication authenticateTest(String username, String password);
    

}
