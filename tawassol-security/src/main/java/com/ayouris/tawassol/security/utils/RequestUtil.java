package com.ayouris.tawassol.security.utils;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.PhaseInterceptorChain;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UrlPathHelper;

import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.CycleBean;
import com.ayouris.tawassol.common.model.entity.Cycle;
import com.ayouris.tawassol.common.util.EncodageConstants;
import com.ayouris.tawassol.common.util.UnicodeStringUtils;
import com.ayouris.tawassol.security.service.AffectationCycleSecurityService;
import com.ayouris.tawassol.security.service.CycleSecurityService;

@Service
public class RequestUtil {

    private static AffectationCycleSecurityService affectationCycleService;
    private static CycleSecurityService cycleService;
    private static CustomModelMapper mapper;
    
    @Autowired
    private AffectationCycleSecurityService affectationCycleServiceAutowired;
    @Autowired
    private CycleSecurityService cycleServiceAutowired;
    @Autowired 
    private CustomModelMapper mapperAutowired;
    
    @PostConstruct
    public void init() {
    	mapper = mapperAutowired;
    	cycleService = cycleServiceAutowired;
    	affectationCycleService = affectationCycleServiceAutowired;
    }
    
    public static List<String> resurceEndPoints = Arrays.asList("tawassol/service", "tawassol/common", "tawassol/admin");

    public static List<String> loginEndPoints = Arrays.asList("/authenticate", "/authenticateForm",
            "/authenticateToken", "/authenticatePartner");

    public static final String INDEX_LINK = "/index.html";
    public static final String RESOURCES_LINK = "/resources/*";
    public static final String LOGOUT_LINK = "/logout";
    public static final String HEADER_TOKEN = "x-auth-token";
    public static final String REMOTE_USER = "REMOTE_USER";
    public static final String USERNAME = "username";
    public static final String FORWARDED_IP = "X-FORWARDED-FOR";
    public static final String LINK_TOKEN = "token";
    public static final String HEADER_LANG = "X-Lang";
    public static final String LINK_LANG = "Xlang";
	public static final String HEADER_CURRENT_CYCLE = "x-current-cycle";

    public static void setResponseHeader(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "36000");
        response.setHeader("Access-Control-Allow-Headers",
                "x-requested-with, Origin, X-Requested-With, Content-Type, Accept, X-Auth-Token, X-Lang,x-current-cycle, REMOTE_USER, username, X-filename, Data-reference");
        response.setHeader("Access-Control-Expose-Headers", 
                "x-auth-token, X-Lang, x-current-cycle REMOTE_USER, username, X-filename, Data-reference");
        response.setHeader("Cache-Control", "no-cache, no-store, no-transform");
        response.setCharacterEncoding(EncodageConstants.UTF8);
    }

    public static boolean isRequiresAuthentication(HttpServletRequest request) {
        String resourcePath = new UrlPathHelper().getPathWithinApplication(request);
        if (isLoginRequest(request) || isLogoutRequest(request)
                || "OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return false;
        }

        return true; 
    }

    public static boolean isResourcesRequest(String url) {
        for (String endPoint : resurceEndPoints) {
            if (url.contains(endPoint)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isIndexRequest(HttpServletRequest httpRequest) {
        if (new AntPathRequestMatcher(RequestUtil.INDEX_LINK, "GET").matches(httpRequest)
                || new AntPathRequestMatcher(RequestUtil.RESOURCES_LINK, "GET").matches(httpRequest)) {
            return true;
        } else if (httpRequest.getPathInfo() == null) {
            return true;
        }
        return false;
    }

    public static boolean isLogoutRequest(HttpServletRequest httpRequest) {
        if (currentLink(httpRequest).endsWith(RequestUtil.LOGOUT_LINK)) {
            return true;
        }
        return false;
    }

    public static boolean isLoginRequest(HttpServletRequest httpRequest) {
        String currentLink = currentLink(httpRequest);
        for (String endPoint : loginEndPoints) {
            if (currentLink.endsWith(endPoint)) {
                return true;
            }
        }
        return false;
    }

    public static String currentLink(HttpServletRequest httpRequest) {
        // String resourcePath = new
        // UrlPathHelper().getPathWithinApplication(request);
        if (httpRequest.getPathInfo() == null) {
            return httpRequest.getServletPath();
        }
        return httpRequest.getServletPath() + httpRequest.getPathInfo();
    }

    public static HttpServletRequest getAsHttpRequest(ServletRequest request) {
        if (!(request instanceof HttpServletRequest)) {
            throw new RuntimeException("Expecting an HTTP request");
        }

        return (HttpServletRequest) request;
    }

    public static String extractAuthTokenFromRequest(HttpServletRequest httpRequest) {
        /** Get token from header **/
        String authToken = httpRequest.getHeader(RequestUtil.HEADER_TOKEN);
        /** If token not found get it from request parameter **/
        if (authToken == null) {
            authToken = httpRequest.getParameter(RequestUtil.LINK_TOKEN);
        }

        return UnicodeStringUtils.unicode2String(authToken);
    }

    public static String extractLangFromRequest(HttpServletRequest httpRequest, String defaultLang) {
        /** Get lang from header **/
        String currentLang = httpRequest.getHeader(RequestUtil.HEADER_LANG);
        /** If lang not found get it from request parameter **/
        if (currentLang == null) {
            currentLang = httpRequest.getParameter(RequestUtil.LINK_LANG);
        }
        /** return default lang **/
        if (currentLang == null) {
            currentLang = defaultLang;
        }
        return currentLang;
    }

    public static String getCurrentIpAddress(HttpServletRequest request) throws UnknownHostException {
        String ipAddress = request.getHeader(RequestUtil.FORWARDED_IP);
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }

    public static String extractRemoteUserFromRequest(HttpServletRequest httpRequest) {
        /** Get token from header **/
        String authToken = httpRequest.getHeader(RequestUtil.REMOTE_USER);
        /** If token not found get it from request parameter **/
        if (authToken == null) {
            authToken = httpRequest.getParameter(RequestUtil.REMOTE_USER);
        }

        return authToken;
    }

    public static String extractUsernameFromRequest(HttpServletRequest httpRequest) {
        /** Get token from header **/
        String authToken = httpRequest.getHeader(RequestUtil.REMOTE_USER);
        /** If token not found get it from request parameter **/
        if (authToken == null) {
            authToken = httpRequest.getParameter(RequestUtil.REMOTE_USER);
        }

        return authToken;
    }

    public static String getCurrentIpAddress() throws UnknownHostException {
        // Here We Capture/Extracting Client IP Address Using CXF
        Message message = PhaseInterceptorChain.getCurrentMessage();
        HttpServletRequest request = (HttpServletRequest) message.get(AbstractHTTPDestination.HTTP_REQUEST);

        String ipAddress = request.getHeader(RequestUtil.FORWARDED_IP);
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }

	public static Cycle extractCurrentCycleFromRequest(HttpServletRequest request) {
		String currentCycleId = request.getHeader(RequestUtil.HEADER_CURRENT_CYCLE);
		Cycle currentCycle = null; 
		if(NumberUtils.isNumber(currentCycleId)) {
			currentCycle = cycleService.findOne(Long.valueOf(currentCycleId));
		}
        if (currentCycle == null) {
            currentCycle = extractCurrentCycle();
        }
        return currentCycle;
   }

	public static Cycle extractCurrentCycle() {
		Cycle currentCycle;
		List<CycleBean> cycles = affectationCycleService.getCurrentCycles();
		currentCycle = mapper.map(cycles.get(cycles.size()),Cycle.class);
		return currentCycle;
	}

}
