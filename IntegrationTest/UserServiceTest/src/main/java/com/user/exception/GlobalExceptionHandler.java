package com.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NameShouldNotBeNullException.class)
    public ResponseEntity<String> HandleNameShouldNotBeNullException(NameShouldNotBeNullException e1){
       return new ResponseEntity<>(e1.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailShouldNotBeNullException.class)
    public ResponseEntity<String> HandleEmailShouldNotBeNullException(EmailShouldNotBeNullException e2){
        return new ResponseEntity<>(e2.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserIdShouldNotBeNullException.class)
    public ResponseEntity<String> HandleUserIdShouldNotBeNullException(UserIdShouldNotBeNullException e3){
        return new ResponseEntity<>(e3.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> HandleUserAlreadyExistsExceptionException(UserAlreadyExistsException e4){
        return new ResponseEntity<>(e4.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IdDoesNotExistsException.class)
    public ResponseEntity<String> HandleIdDoesNotExistsExceptionException(IdDoesNotExistsException e5){
        return new ResponseEntity<>(e5.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
