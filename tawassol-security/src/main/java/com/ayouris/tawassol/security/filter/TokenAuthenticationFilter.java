package com.ayouris.tawassol.security.filter;

import java.io.IOException;
import java.net.UnknownHostException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.UrlPathHelper;

import com.ayouris.tawassol.admin.model.entity.User;
import com.ayouris.tawassol.common.exception.ErrorMessageType;
import com.ayouris.tawassol.common.model.entity.Cycle;
import com.ayouris.tawassol.common.util.EncodageConstants;
import com.ayouris.tawassol.security.exception.AuthorizationException;
import com.ayouris.tawassol.security.service.AuthenticateService;
import com.ayouris.tawassol.security.utils.AuthenticationChecker;
import com.ayouris.tawassol.security.utils.RequestUtil;
import com.ayouris.tawassol.security.utils.TokenUtils;


@Component("tokenAuthenticationFilter")
public class TokenAuthenticationFilter extends GenericFilterBean {

    private final Logger LOGGER = LoggerFactory.getLogger(TokenAuthenticationFilter.class);

    @Autowired
    protected AuthenticateService authenticateService;

    @Override
    public void doFilter(final ServletRequest httpRequest, final ServletResponse httpResponse, final FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) httpResponse;

        RequestUtil.setResponseHeader(response);

        HttpServletRequest request = RequestUtil.getAsHttpRequest(httpRequest);
        request.setCharacterEncoding(EncodageConstants.UTF8);

        try {

            String remoteIpAddr = RequestUtil.getCurrentIpAddress(request);

            if (!RequestUtil.isRequiresAuthentication(request)) {
                chain.doFilter(request, response);
                return;
            }

            String token = RequestUtil.extractAuthTokenFromRequest(request);

            User user = authenticateService.checkToken(token, RequestUtil.getCurrentIpAddress(request));

            response.setHeader(RequestUtil.HEADER_TOKEN, TokenUtils.createToken(remoteIpAddr, user));

            AuthenticationChecker.checkUserIsNotNull(user);
            AuthenticationChecker.checkEnableAccount(user);
            
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("RemoteAddr {" + remoteIpAddr + "} Trying to the secured resource: { "
                        + new UrlPathHelper().getPathWithinApplication(request) + " }. username: { "
                        + user.getUsername() + " }");
            }

            Cycle currentCycle = RequestUtil.extractCurrentCycleFromRequest(request);
            user.setCurrentCycle(currentCycle);
            response.setHeader(RequestUtil.HEADER_CURRENT_CYCLE, TokenUtils.createToken(remoteIpAddr, user));
            
            setAuthenticateUser(user, request, response);

            chain.doFilter(request, response);

        } catch (AuthorizationException ex) {
            SecurityContextHolder.clearContext();
            LOGGER.error(ex.getError().getMessage());
            response.sendError(ex.getError().getHttpResponseStatus(), ex.getError().getFieldName());
        } catch (Exception ex) {
            SecurityContextHolder.clearContext();
            LOGGER.error("internal authentification service exception", ex);
            response.sendError(ErrorMessageType.INTERNAL_AUTHEN_ERROR.getHttpResponseStatus(),
                    ErrorMessageType.INTERNAL_AUTHEN_ERROR.getFieldName());
        }

    }

    @Transactional
    private void setAuthenticateUser(User user, HttpServletRequest request, HttpServletResponse response)
            throws UnknownHostException {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null,
                user.getAuthorities());

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);

    }

}
