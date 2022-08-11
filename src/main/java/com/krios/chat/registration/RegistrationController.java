package com.krios.chat.registration;

import com.krios.chat.appuser.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "/api/registration")
@CrossOrigin
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<AppUser> register(@RequestParam Map<String, String> payload) {
        AppUser user = registrationService.register(payload);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}