package ma.salamgaz.tawassol.security.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import ma.salamgaz.tawassol.common.model.bean.UserDataAuthenticate;
import ma.salamgaz.tawassol.security.model.UserData;
import ma.salamgaz.tawassol.security.service.AuthenticateService;

@Component()
public class AuthenticateControllerImpl implements AuthenticateController {

	@Autowired
	private AuthenticateService authenticateService;

	public UserData authenticateForm(String username, char[] password,HttpServletRequest request) {
		return authenticateService.authenticateForm(username, new String(password),request);
	}

    public ResponseEntity<UserData> authenticate(@RequestBody UserDataAuthenticate user,HttpServletRequest request) throws Exception {
        return new ResponseEntity<UserData>(authenticateForm(user.getUsername(), user.getPassword(),request), HttpStatus.OK);
    }

}
