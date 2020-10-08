package com.cinema.exception;

public class PasswordEncryptionException extends RuntimeException {
    public PasswordEncryptionException(String message, Throwable ex) {
        super(message, ex);
    }
}
