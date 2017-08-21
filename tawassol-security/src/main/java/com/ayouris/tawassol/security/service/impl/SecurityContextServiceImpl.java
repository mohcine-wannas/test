package com.ayouris.tawassol.security.service.impl;

import com.ayouris.tawassol.security.model.User;
import com.ayouris.tawassol.security.repository.UserRepository;
import com.ayouris.tawassol.security.service.SecurityContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityContextServiceImpl implements SecurityContextService {

    private final UserRepository userRepository;

    @Autowired
    public SecurityContextServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User currentUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final Optional<User> currentUser = userRepository.findOneByUsername(authentication.getName());
        // TODO It may be better to return optional.
        return currentUser.orElse(null);
    }
}
