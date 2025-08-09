package com.user.exception;

public class EmailShouldNotBeNullException extends RuntimeException{
    public EmailShouldNotBeNullException(String message){
        super(message);
    }
}
