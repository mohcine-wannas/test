package com.ayouris.tawassol.security.service.impl;

import com.ayouris.tawassol.security.auth.TokenHandler;
import com.ayouris.tawassol.security.auth.UserAuthentication;
import com.ayouris.tawassol.security.service.TokenAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class TokenAuthenticationServiceImpl implements TokenAuthenticationService {

    @Value("${jwt.header}")
    private String tokenHeader;

    private final TokenHandler tokenHandler;

    @Autowired
    public TokenAuthenticationServiceImpl(TokenHandler tokenHandler) {
        this.tokenHandler = tokenHandler;
    }

    public void addAuthentication(HttpServletResponse response,
                           UserAuthentication authentication) {
        final UserDetails user = authentication.getDetails();
        response.addHeader(tokenHeader, tokenHandler.createTokenForUser(user));
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        final String token = request.getHeader(tokenHeader);
        return tokenHandler
                .parseUserFromToken(token)
                .map(UserAuthentication::new)
                .orElse(null);
    }
}

