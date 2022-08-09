package com.krios.chat.login;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/login")
@CrossOrigin
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> login(@RequestParam Map<String, String> payload, HttpServletResponse response) {
        String jwtToken = loginService.login(payload);

        Cookie cookie = new Cookie("JWT", jwtToken);
        response.addCookie(cookie);

        return new ResponseEntity<>("JWT was sent in a cookie", HttpStatus.OK);
    }
}