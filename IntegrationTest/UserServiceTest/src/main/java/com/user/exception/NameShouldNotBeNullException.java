package com.user.exception;

public class NameShouldNotBeNullException extends RuntimeException{
    public NameShouldNotBeNullException(String message){
        super(message);
    }
}
