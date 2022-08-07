package com.krios.chat.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

public class JWTUtils {
    // TODO: Secure secret
    private static final Algorithm ALGORITHM = Algorithm.HMAC256("secret");

    public static String buildAccessToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + 5 * 60 * 1000)) // 5 min
                .sign(ALGORITHM);
    }
}