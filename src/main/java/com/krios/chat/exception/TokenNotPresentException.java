package com.krios.chat.exception;

public class TokenNotPresentException extends NullPointerException {
    public TokenNotPresentException(String message) {
        super(message);
    }
}
