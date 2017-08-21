package com.ayouris.tawassol.security.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import com.ayouris.tawassol.security.model.User;
import com.ayouris.tawassol.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;

@Component
public final class TokenHandler {

    private final UserService userService;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${jwt.bearer-token}")
    private Boolean isBearerToken;


    @Autowired
    public TokenHandler(UserService userService) {
        this.userService = userService;
    }

    public String createTokenForUser(UserDetails user) {
        final ZonedDateTime duration = ZonedDateTime.now().plusMinutes(120);
        return Jwts.builder()
                .setSubject(user.getUsername())
                .signWith(SignatureAlgorithm.HS512, secret)
                .setExpiration(Date.from(duration.toInstant()))
                .compact();
    }

    public Optional<UserDetails> parseUserFromToken(String token) {
        if (isTokenMalformed(token)) {
            return null;
        }

        String username = getUsernameFromToken(token);

        if (username == null) {
            return null;
        } else {
            return Optional.ofNullable(userService.loadUserByUsername(username));
        }
    }

    public String getUsernameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }


    public Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            if (isBearerToken) {
                token = token.substring(7);
            }
            //@formatter:off
            claims = Jwts
	            		.parser()
	                    .setSigningKey(secret)
	                    .parseClaimsJws(token)
	                    .getBody();
        	//@formatter:on
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }


    private Boolean isTokenExpired(String token) {
        try {
            if (isBearerToken) {
                token = token.substring(7);
            }
            final Date expiration = getExpirationDateFromToken(token);
            return expiration.before(new Date());
        } catch (Exception e) {
            return true;
        }
    }

    public Boolean isTokenMalformed(String token) {
        if (null != token) {
            if (isBearerToken && !token.startsWith("Bearer ")) {
                return true;
            }
        }
        return false;
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        User user = (User) userDetails;
        final String username = getUsernameFromToken(token);
        return (username.equals(user.getUsername()) && !isTokenExpired(token));
    }

    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }
}

