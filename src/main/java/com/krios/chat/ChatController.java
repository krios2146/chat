package com.krios.chat;

import com.krios.chat.message.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@CrossOrigin(origins = "http://localhost:4200/")
public class ChatController {

    @MessageMapping("/chat/sendMessage")
    @SendTo("/topic/public")
    public Message sendMessage(@Payload String message) {
        return new Message(message);
    }

}
