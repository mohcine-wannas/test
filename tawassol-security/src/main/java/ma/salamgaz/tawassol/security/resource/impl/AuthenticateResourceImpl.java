package ma.salamgaz.tawassol.security.resource.impl;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ma.salamgaz.tawassol.common.exception.ErrorMessageType;
import ma.salamgaz.tawassol.common.model.bean.TokenData;
import ma.salamgaz.tawassol.common.model.bean.UserDataAuthenticate;
import ma.salamgaz.tawassol.admin.model.entity.User;
import ma.salamgaz.tawassol.security.model.ErrorMessageResponse;
import ma.salamgaz.tawassol.security.model.UserData;
import ma.salamgaz.tawassol.security.resource.AuthenticateResource;
import ma.salamgaz.tawassol.security.service.AuthenticateService;

@Component(value = "authResource")
public class AuthenticateResourceImpl implements AuthenticateResource<User, Long> {

	@Autowired
	private AuthenticateService authenticateService;

	@Override
	public Response authenticateForm(String username, char[] password) {

		return processeResponse(authenticateService.authenticateForm(username, new String(password)));

	}

	@Override
	public Response authenticate(UserDataAuthenticate user) {

		return authenticateForm(user.getUsername(), user.getPassword());
	}

	@Override
	public Response authenticateToken(String token) {

		return processeResponse(authenticateService.authenticateToken(token));

	}

	@Override
	public Response authenticateRemoteUser(String username) {

		return processeResponse(authenticateService.authenticateRemoteUser(username));
	}

	@Override
	public Response logout() {

		return Response.ok(authenticateService.logout()).build();
	}

	@Override
	public Response authenticatePartner(UserDataAuthenticate user) {

		UserData userData = authenticateService.authenticateForm(user.getUsername(), new String(user.getPassword()));

		if (userData == null) {
			return Response.status(ErrorMessageType.USER_CREDENTIALS_INVALID.getHttpResponseStatus())
					.entity(new ErrorMessageResponse(ErrorMessageType.USER_CREDENTIALS_INVALID.getCode(),
							ErrorMessageType.USER_CREDENTIALS_INVALID.getFieldName(),
							ErrorMessageType.USER_CREDENTIALS_INVALID.getMessage()))
					.type(MediaType.APPLICATION_JSON).build();
		}
		return Response.ok(new TokenData(userData.getToken())).build();
	}

	private Response processeResponse(UserData userData) {
		if (userData == null) {
			return Response.status(ErrorMessageType.USER_CREDENTIALS_INVALID.getHttpResponseStatus())
					.entity(new ErrorMessageResponse(ErrorMessageType.USER_CREDENTIALS_INVALID.getCode(),
							ErrorMessageType.USER_CREDENTIALS_INVALID.getFieldName(),
							ErrorMessageType.USER_CREDENTIALS_INVALID.getMessage()))
					.type(MediaType.APPLICATION_JSON).build();
		}

		return Response.ok(userData).build();
	}

}
