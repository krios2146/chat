package com.krios.chat.registration;

import com.krios.chat.appuser.AppUser;
import com.krios.chat.appuser.AppUserService;
import com.krios.chat.appuser.role.Role;
import com.krios.chat.appuser.role.RoleEnum;
import com.krios.chat.exception.InvalidEmailException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;

    public AppUser register(RegistrationRequest request) {
        boolean isEmailValid = Pattern.matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", request.getEmail());

        if (!isEmailValid) {
            throw new InvalidEmailException("Email: " + request.getEmail() + " is not valid");
        }

        return appUserService.registerUser(
                new AppUser(
                        request.getUsername(),
                        request.getEmail(),
                        request.getPassword(),
                        request.getFirstName(),
                        request.getLastName(),
                        List.of(new Role(RoleEnum.ROLE_USER))
                )
        );
    }
}
