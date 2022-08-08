package com.krios.chat.security.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.krios.chat.appuser.AppUserService;
import com.krios.chat.exception.TokenNotPresentException;
import com.krios.chat.security.jwt.JWTUtils;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@NoArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private AppUserService appUserService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = JWTUtils.getTokenFromRequest(request);

        if (jwtToken == null) {
            // TODO: Set 401 status to response instead of 500
            filterChain.doFilter(request, response);
            throw new TokenNotPresentException("JWT token was not found in request");
        }

        DecodedJWT decodedJWT = JWTUtils.decodeJwtToken(jwtToken);

        String username = decodedJWT.getSubject();
        UserDetails userDetails = appUserService.loadUserByUsername(username);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);
    }
}
