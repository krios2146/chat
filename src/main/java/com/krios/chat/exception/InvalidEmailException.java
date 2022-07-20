package com.krios.chat.exception;

public class InvalidEmailException extends IllegalStateException {
    public InvalidEmailException(String message) {
        super(message);
    }
}
