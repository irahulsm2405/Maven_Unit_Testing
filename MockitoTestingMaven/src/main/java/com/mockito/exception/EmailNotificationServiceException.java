package com.mockito.exception;

public class EmailNotificationServiceException extends RuntimeException{
    EmailNotificationServiceException(String message){
        super(message);
    }
}
