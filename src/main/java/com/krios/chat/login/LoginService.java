package com.krios.chat.login;

import com.krios.chat.appuser.AppUser;
import com.krios.chat.appuser.AppUserService;
import com.krios.chat.security.jwt.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;

    /**
     * Do authentication first, then create JWT token and return this token
     * @param payload should contain "username" and "password" keys
     * @return JWT Access Token
     */
    public String login(Map<String, String> payload) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    payload.get("username"),
                    payload.get("password")
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        AppUser appUser = (AppUser) authentication.getPrincipal();

        return JWTUtils.buildAccessToken(appUser);
    }
}