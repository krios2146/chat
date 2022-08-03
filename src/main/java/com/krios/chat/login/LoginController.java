package com.krios.chat.login;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;

@RestController
@RequestMapping(path = "/api/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping(consumes = {APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<Map<String, String>> login(@RequestBody MultiValueMap<String, String> formData) {
        Map<String, String> tokens = loginService.login(formData);
        return new ResponseEntity<>(tokens, OK);
    }
}
