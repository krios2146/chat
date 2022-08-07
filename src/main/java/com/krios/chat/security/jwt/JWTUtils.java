package com.krios.chat.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.krios.chat.appuser.AppUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.stream.Collectors;

public class JWTUtils {
    // TODO: Secure secret
    private static final Algorithm ALGORITHM = Algorithm.HMAC256("secret");

    public static String buildAccessToken(AppUser appUser) {
        return JWT.create()
                .withSubject(appUser.getUsername())
                .withClaim("roles", appUser.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .withExpiresAt(new Date(System.currentTimeMillis() + 5 * 60 * 1000)) // 5 min
                .sign(ALGORITHM);
    }
}