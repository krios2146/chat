package com.krios.chat.login;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;

@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public Principal login(Principal principal) {
        return principal;
    }
}
