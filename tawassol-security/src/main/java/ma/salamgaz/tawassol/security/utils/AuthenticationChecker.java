package ma.salamgaz.tawassol.security.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;

import ma.salamgaz.tawassol.admin.model.entity.User;
import ma.salamgaz.tawassol.common.exception.ErrorMessageType;
import ma.salamgaz.tawassol.security.exception.AuthorizationException;

public class AuthenticationChecker {

    public static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationChecker.class);

    public static void checkUserIsNotNull(User user) throws AuthorizationException {

        if (user == null) {
            LOGGER.error(ErrorMessageType.USER_NOT_FOUND.getMessage());
            throw new AuthorizationException(ErrorMessageType.USER_NOT_FOUND);
        }
    }

    public static void checkEnableAccount(User user) throws AuthorizationException {

        if (!SecurityUtils.checkEnableAccount(user)) {
            LOGGER.error(ErrorMessageType.USER_ACCOUNT_DISABLED.getMessage());
            throw new AuthorizationException(ErrorMessageType.USER_ACCOUNT_DISABLED);
        }
    }

    public static void checkValidateToken(String token, String ipAddres, UserDetails user) {

        if (!TokenUtils.validateToken(token, ipAddres, user)) {
            LOGGER.error(ErrorMessageType.TEMPORARY_TOKEN_INVALID.getMessage() + ". token {" + token + "}");
            throw new AuthorizationException(ErrorMessageType.TEMPORARY_TOKEN_INVALID);
        }
    }

    public static void checkTokenIsNotNull(String token) throws AuthorizationException {

        if (token == null) {
            LOGGER.error(ErrorMessageType.SIGNATURE_OR_TOKEN_INVALID.getMessage());
            throw new AuthorizationException(ErrorMessageType.SIGNATURE_OR_TOKEN_INVALID);
        }
    }

    public static void checkUsernameIsNotNull(String username) throws AuthorizationException {

        if (username == null) {
            LOGGER.error(ErrorMessageType.SIGNATURE_OR_TOKEN_INVALID.getMessage());
            throw new AuthorizationException(ErrorMessageType.SIGNATURE_OR_TOKEN_INVALID);
        }

    }

    public static void checkRemoteUserIsNotNull(String remoteUser) throws AuthorizationException {

        if (remoteUser == null) {
            LOGGER.error(ErrorMessageType.REMOTE_USER_NOT_FOUND.getMessage());
            throw new AuthorizationException(ErrorMessageType.REMOTE_USER_NOT_FOUND);
        }
    }

    public static void checkRemoteUserEqualsUsername(String remoteUser, String username) throws AuthorizationException {

        checkRemoteUserIsNotNull(remoteUser);
        if (!remoteUser.equals(username)) {
            LOGGER.error(ErrorMessageType.USENAME_INVALIDE.getMessage());
            throw new AuthorizationException(ErrorMessageType.USENAME_INVALIDE);
        }
    }

}
