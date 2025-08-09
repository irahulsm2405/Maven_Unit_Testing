package com.user.exception;

public class IdDoesNotExistsException extends RuntimeException{
    public IdDoesNotExistsException(String message){
        super(message);
    }
}
