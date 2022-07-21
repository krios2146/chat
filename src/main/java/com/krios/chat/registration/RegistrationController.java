package com.krios.chat.registration;

import com.krios.chat.appuser.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping(path = "/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<AppUser> register(@RequestBody RegistrationRequest request) {
        AppUser user = registrationService.register(request);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
