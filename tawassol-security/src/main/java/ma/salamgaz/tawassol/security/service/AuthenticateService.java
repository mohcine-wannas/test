package ma.salamgaz.tawassol.security.service;

import javax.servlet.http.HttpServletRequest;

import ma.salamgaz.tawassol.admin.model.entity.User;
import ma.salamgaz.tawassol.security.exception.AuthorizationException;
import ma.salamgaz.tawassol.security.model.UserData;

public interface AuthenticateService {

    UserData authenticateForm(String username, String password, HttpServletRequest request);

    UserData authenticateToken(String token);

    User checkToken(String token, String ipAddres) throws AuthorizationException;

    User findUserByUsername(String username);

    UserData authenticateRemoteUser(String username);

    UserData logout();
    

}
