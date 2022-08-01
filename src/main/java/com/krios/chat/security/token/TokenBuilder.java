package com.krios.chat.security.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.krios.chat.appuser.AppUser;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import java.util.Date;
import java.util.stream.Collectors;

public class TokenBuilder {
    // TODO: Secure secret
    private static final Algorithm ALGORITHM = Algorithm.HMAC256("secret");

    public static String buildAccessToken(AppUser user, HttpServletRequest request) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 5 * 60 * 1000)) // 5 min
                .withIssuer(request.getRequestURI())
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(ALGORITHM);
    }

    public static String buildRefreshToken(AppUser user, HttpServletRequest request) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000)) // 60 min
                .withIssuer(request.getRequestURI())
                .sign(ALGORITHM);
    }
}
