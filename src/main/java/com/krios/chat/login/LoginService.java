package com.krios.chat.login;

import com.krios.chat.appuser.AppUser;
import com.krios.chat.appuser.AppUserService;
import com.krios.chat.security.token.TokenBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final AppUserService appUserService;

    public Map<String, String> login(MultiValueMap<String, String> formData) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                appUserService.loadUserByUsername(formData.get("username").get(0)),
                formData.get("password")
        );
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        Object principal = authentication.getPrincipal();
        AppUser user = new AppUser();

        String accessToken = TokenBuilder.buildAccessToken(user);
        String refreshToken = TokenBuilder.buildRefreshToken(user);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", accessToken);
        tokens.put("refresh_token", refreshToken);
        return tokens;
    }
}
