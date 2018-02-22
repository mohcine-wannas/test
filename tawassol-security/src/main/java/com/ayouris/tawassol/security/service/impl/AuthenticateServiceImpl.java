package com.ayouris.tawassol.security.service.impl;

import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ayouris.tawassol.admin.model.entity.User;
import com.ayouris.tawassol.common.exception.ErrorMessageType;
import com.ayouris.tawassol.security.exception.AuthorizationException;
import com.ayouris.tawassol.security.model.UserContextResponse;
import com.ayouris.tawassol.security.model.UserData;
import com.ayouris.tawassol.security.service.AuthenticateService;
import com.ayouris.tawassol.security.service.UserService;
import com.ayouris.tawassol.security.utils.AuthenticationChecker;
import com.ayouris.tawassol.security.utils.RequestUtil;
import com.ayouris.tawassol.security.utils.SecurityUtils;
import com.ayouris.tawassol.security.utils.TokenUtils;

//@Configuration
@Service("authenticateSecurityService")
public class AuthenticateServiceImpl implements AuthenticateService {

	private final Logger LOGGER = LoggerFactory.getLogger(AuthenticateServiceImpl.class);

	@Autowired
	private UserService userService;

	@Autowired
	// @Qualifier("authenticationManager")
	private AuthenticationManager authManager;

	@Override
	public UserData authenticateForm(String username, String password,HttpServletRequest request) {

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
				password);
		String remoteAddr = null;
		try {

			remoteAddr = RequestUtil.getCurrentIpAddress(request);

			Authentication authentication = this.authManager.authenticate(authenticationToken);
			SecurityContextHolder.getContext().setAuthentication(authentication);

			User user = (User) authentication.getPrincipal();

			UserContextResponse userResponse = SecurityUtils.getCurrentUserContextResponse();

			if (userResponse.getPermissionModel() == null) {
				LOGGER.error("RemoteAddr {" + remoteAddr + "}, Invalid permission model for user :  " + username);
				return null;
			}

			return new UserData(false, TokenUtils.createToken(remoteAddr, user), userResponse);

		} catch (DisabledException | LockedException | InternalAuthenticationServiceException
				| BadCredentialsException ex) {
			LOGGER.error("RemoteAddr {" + remoteAddr + "}, "
					+ ErrorMessageType.USER_CREDENTIALS_INVALID.getMessage(username));
			return null;
		} catch (Exception ex) {
			LOGGER.error("RemoteAddr {" + remoteAddr + "}, " + ErrorMessageType.SC_INTERNAL_SERVER_ERROR.getMessage(),
					ex);
			return null;
		}

	}



	@Override
	public UserData authenticateToken(String token) {

		try {

			String remoteAddr = RequestUtil.getCurrentIpAddress();
			User user = checkToken(token, remoteAddr);

			UserContextResponse userResponse = SecurityUtils.getCurrentUserContextResponse(user);
			if (userResponse.getPermissionModel() == null) {
				LOGGER.error(
						"RemoteAddr {" + remoteAddr + "}, Invalid permission model for user :  " + user.getUsername());
				return null;
			}

			return new UserData(false, TokenUtils.createToken(remoteAddr, user), userResponse);

		} catch (AuthorizationException ex) {
			LOGGER.error(ex.getError() + ". token {" + token + "}");
			return null;
		} catch (UnknownHostException ex) {
			LOGGER.error(ErrorMessageType.INTERNAL_AUTHEN_ERROR.getMessage(token) + ". token {" + token + "}");
			return null;
		}

	}
	
	@Override
	public Authentication authenticateTest(String username, String password) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
				password);
		try {

			Authentication authentication = this.authManager.authenticate(authenticationToken);
			return authentication;

		} catch (DisabledException | LockedException | InternalAuthenticationServiceException
				| BadCredentialsException ex) {
			throw new ServiceException(ErrorMessageType.WORNG_PASSWORD.getMessage());

		}
	}

	@Override
	public UserData authenticateRemoteUser(String username) {
		try {

			User user = findUserByUsername(username);

			UserContextResponse userResponse = SecurityUtils.getCurrentUserContextResponse(user);
			if (userResponse.getPermissionModel() == null) {
				LOGGER.error("RemoteAddr {" + RequestUtil.getCurrentIpAddress()
						+ "}, Invalid permission model for user :  " + user.getUsername());
				return null;
			}

			return new UserData(true, null, userResponse);

		} catch (AuthorizationException ex) {
			LOGGER.error(ex.getError() + ". username {" + username + "}");
			return null;
		} catch (UnknownHostException ex) {
			LOGGER.error(ErrorMessageType.INTERNAL_AUTHEN_ERROR.getMessage(username) + ". username {" + username + "}");
			return null;
		}
	}

	@Override
	public User findUserByUsername(String username) {
		return this.userService.findUserByUsername(username);
	}

	@Override
	public User checkToken(String token, String ipAddres) throws AuthorizationException {

		AuthenticationChecker.checkTokenIsNotNull(token);

		String username = TokenUtils.getUserNameFromToken(token);

		AuthenticationChecker.checkUsernameIsNotNull(username);

		User user = this.userService.findUserByUsername(username);

		AuthenticationChecker.checkUserIsNotNull(user);

		AuthenticationChecker.checkEnableAccount(user);

		AuthenticationChecker.checkValidateToken(token, ipAddres, user);

		return user;
	}

	@Override
	public UserData logout() {
		SecurityContextHolder.getContext().setAuthentication(null);
		return new UserData(false, null, null);
	}






}
