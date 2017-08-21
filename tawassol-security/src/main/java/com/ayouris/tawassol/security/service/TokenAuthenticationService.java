package com.ayouris.tawassol.security.service;

import com.ayouris.tawassol.security.auth.UserAuthentication;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface TokenAuthenticationService {

    void addAuthentication(HttpServletResponse response, UserAuthentication authentication);

    Authentication getAuthentication(HttpServletRequest request);
}

