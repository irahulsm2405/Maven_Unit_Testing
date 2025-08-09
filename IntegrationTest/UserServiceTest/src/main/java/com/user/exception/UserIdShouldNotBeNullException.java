package com.user.exception;

public class UserIdShouldNotBeNullException extends RuntimeException{
    public UserIdShouldNotBeNullException(String message){
        super(message);
    }
}
