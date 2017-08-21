package com.ayouris.tawassol.security.service;


import com.ayouris.tawassol.security.model.User;

public interface SecurityContextService {
    User currentUser();
}
