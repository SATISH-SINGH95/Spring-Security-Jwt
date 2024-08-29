package com.demoJWT.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntityNotFoundException extends RuntimeException{

    private String message;
    private HttpStatus status;


}
