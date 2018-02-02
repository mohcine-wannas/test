package ma.salamgaz.gwic.security.service.impl;

import java.net.UnknownHostException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import ma.salamgaz.gwic.common.exception.ErrorMessageType;
import ma.salamgaz.gwic.common.model.bean.UserDataAuthenticate;
import ma.salamgaz.gwic.admin.model.entity.User;

import ma.salamgaz.gwic.security.exception.AuthorizationException;
import ma.salamgaz.gwic.security.model.UserContextResponse;
import ma.salamgaz.gwic.security.model.UserData;
import ma.salamgaz.gwic.security.service.AuthenticateService;
import ma.salamgaz.gwic.security.service.UserService;
import ma.salamgaz.gwic.security.utils.AuthenticationChecker;
import ma.salamgaz.gwic.security.utils.RequestUtil;
import ma.salamgaz.gwic.security.utils.SecurityUtils;
import ma.salamgaz.gwic.security.utils.TokenUtils;
import ma.salamgaz.gwic.security.utils.UserAuthentication;

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
	public UserData authenticateForm(String username, String password) {

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
				password);
		String remoteAddr = null;
		try {

			remoteAddr = RequestUtil.getCurrentIpAddress();

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
	public UserData authenticate(UserDataAuthenticate user) {

		return authenticateForm(user.getUsername(), new String(user.getPassword()));
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

	public void addAuthentication(HttpServletResponse response, UserAuthentication authentication) throws UnknownHostException {
		final UserDetails user = authentication.getDetails();
		response.setHeader(RequestUtil.HEADER_TOKEN, TokenUtils.createToken(RequestUtil.getCurrentIpAddress(), user));
	}

}
