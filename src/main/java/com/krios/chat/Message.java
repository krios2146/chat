package com.krios.chat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
    private Long id;
    private Long chatId;
    private Long senderId;
    private Long recipientId;
    private String text;
}
