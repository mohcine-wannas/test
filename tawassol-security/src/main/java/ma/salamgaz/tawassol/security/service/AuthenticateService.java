package ma.salamgaz.tawassol.security.service;

import ma.salamgaz.tawassol.common.model.bean.UserDataAuthenticate;

import java.net.UnknownHostException;

import javax.servlet.http.HttpServletResponse;

import ma.salamgaz.tawassol.admin.model.entity.User;
import ma.salamgaz.tawassol.security.exception.AuthorizationException;
import ma.salamgaz.tawassol.security.model.UserData;
import ma.salamgaz.tawassol.security.utils.UserAuthentication;

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
