package com.demoJWT.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> entityNotFoundMethod(EntityNotFoundException ex){
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), ex.getStatus(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, ex.getStatus());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> entityNotFoundMethod(BadRequestException ex){
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), ex.getStatus(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, ex.getStatus());
    }
}
