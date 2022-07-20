package com.krios.chat.exception;

public class EmailAlreadyTakenException extends IllegalStateException {
    public EmailAlreadyTakenException(String message) {
        super(message);
    }
}
