package ma.salamgaz.gwic.security.service;

import ma.salamgaz.gwic.common.model.bean.UserDataAuthenticate;

import java.net.UnknownHostException;

import javax.servlet.http.HttpServletResponse;

import ma.salamgaz.gwic.admin.model.entity.User;
import ma.salamgaz.gwic.security.exception.AuthorizationException;
import ma.salamgaz.gwic.security.model.UserData;
import ma.salamgaz.gwic.security.utils.UserAuthentication;

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
