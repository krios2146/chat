package com.krios.chat.registration;

import com.krios.chat.appuser.AppUser;
import com.krios.chat.appuser.AppUserService;
import com.krios.chat.appuser.role.Role;
import com.krios.chat.appuser.role.RoleEnum;
import com.krios.chat.exception.InvalidEmailException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;

    public AppUser register(Map<String, String> payload) {
        if (payload.size() != 5) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing data in payload");
        }

        boolean isEmailValid = Pattern.matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", payload.get("email"));

        if (!isEmailValid) {
            throw new InvalidEmailException("Email: " + payload.get("email") + " is not valid");
        }

        return appUserService.registerUser(
                new AppUser(
                        payload.get("username"),
                        payload.get("email"),
                        payload.get("password"),
                        payload.get("firstName"),
                        payload.get("lastname"),
                        List.of(new Role(RoleEnum.ROLE_USER))
                )
        );
    }
}