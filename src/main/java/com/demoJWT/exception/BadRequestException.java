package com.demoJWT.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class BadRequestException extends RuntimeException{

    private String message;
    private HttpStatus status;
}
