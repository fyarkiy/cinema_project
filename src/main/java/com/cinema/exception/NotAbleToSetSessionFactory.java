package com.cinema.exception;

public class NotAbleToSetSessionFactory extends RuntimeException {
    public NotAbleToSetSessionFactory(String message, Throwable e) {
        super(message, e);
    }
}
