package com.krios.chat.message;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping(path = "/message")
@RequiredArgsConstructor
public class MessageController {

    public final MessageService messageService;

    @GetMapping(path = "/get/all")
    public ResponseEntity<List<Message>> getAll() {
        return new ResponseEntity<>(messageService.getMessages(), HttpStatus.OK);
    }

}
