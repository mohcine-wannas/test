package ma.salamgaz.gwic.security.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Service;

import ma.salamgaz.gwic.common.exception.ErrorMessageType;
import ma.salamgaz.gwic.security.utils.RequestUtil;

/**
 * {@link AuthenticationEntryPoint} that rejects all requests with an
 * unauthorized error message.
 * 
 */
@Service("unauthorizedEntryPoint")
public class Http401UnauthorizedEntryPoint implements AuthenticationEntryPoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(Http401UnauthorizedEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Pre-authenticated entry point called. Rejecting access");
        }

        RequestUtil.setResponseHeader(response);

        response.sendError(ErrorMessageType.UNAUTHORIZED.getHttpResponseStatus(),
                ErrorMessageType.UNAUTHORIZED.getFieldName());
    }

}