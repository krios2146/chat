package com.krios.chat.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.krios.chat.appuser.AppUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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

    /**
     * Return value of JWT if it's present otherwise {@code null}
     * @param request should contain JWT cookie
     * @return JWT Token value
     */
    public static String getTokenFromRequest(HttpServletRequest request) {
        Cookie jwtCookie = WebUtils.getCookie(request, "JWT");
        return jwtCookie != null ? jwtCookie.getValue() : null;
    }

    public static DecodedJWT decodeJwtToken(String jwtToken) {
        JWTVerifier jwtVerifier = JWT.require(ALGORITHM).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(jwtToken);
        return decodedJWT;
    }
}