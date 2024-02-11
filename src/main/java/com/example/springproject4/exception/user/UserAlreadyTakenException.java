package com.example.springproject4.exception.user;

public class UserAlreadyTakenException extends RuntimeException{
    public UserAlreadyTakenException(String message) {
        super(message);
    }
}
