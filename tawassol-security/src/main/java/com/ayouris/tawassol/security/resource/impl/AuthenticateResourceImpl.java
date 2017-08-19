package com.ayouris.tawassol.security.resource.impl;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ayouris.tawassol.admin.model.entity.User;
import com.ayouris.tawassol.common.exception.ErrorMessageType;
import com.ayouris.tawassol.common.model.bean.TokenData;
import com.ayouris.tawassol.common.model.bean.UserDataAuthenticate;
import com.ayouris.tawassol.security.model.ErrorMessageResponse;
import com.ayouris.tawassol.security.model.UserData;
import com.ayouris.tawassol.security.resource.AuthenticateResource;
import com.ayouris.tawassol.security.service.AuthenticateService;

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
		if(userData.isInactif()) {
			return Response.status(ErrorMessageType.USER_INACTIF.getHttpResponseStatus())
					.entity(new ErrorMessageResponse(ErrorMessageType.USER_INACTIF.getCode(),
							ErrorMessageType.USER_INACTIF.getFieldName(),
							ErrorMessageType.USER_INACTIF.getMessage()))
					.type(MediaType.APPLICATION_JSON).build();
		}

		return Response.ok(userData).build();
	}

}
