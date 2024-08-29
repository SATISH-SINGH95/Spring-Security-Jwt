package com.demoJWT.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class UserNotValidException extends RuntimeException{

    private String message;
    private HttpStatus status;
}
