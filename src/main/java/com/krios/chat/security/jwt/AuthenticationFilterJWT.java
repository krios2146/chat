package com.krios.chat.security.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.krios.chat.appuser.AppUserService;
import com.krios.chat.exception.TokenNotPresentException;
import lombok.NoArgsConstructor;
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
public class AuthenticationFilterJWT extends OncePerRequestFilter {

    @Autowired
    private AppUserService appUserService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (request.getServletPath().contains("login") || request.getServletPath().contains("registration")) {
            filterChain.doFilter(request, response);
        } else {
            String jwtToken = JWTUtils.getTokenFromRequest(request);

            try {
                if (jwtToken == null || jwtToken.isEmpty()) {
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

            } catch (Exception e) {
                e.printStackTrace();
            }

            filterChain.doFilter(request, response);
        }
    }
}