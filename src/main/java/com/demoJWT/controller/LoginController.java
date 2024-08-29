package com.demoJWT.controller;

import com.demoJWT.constant.UserConstants;
import com.demoJWT.exception.BadRequestException;
import com.demoJWT.exception.UserNotValidException;
import com.demoJWT.model.JwtResponse;
import com.demoJWT.model.LoginRequest;
import com.demoJWT.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@Slf4j
public class LoginController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> verify(@RequestBody LoginRequest loginRequest){
         try {
             Authentication authentication = authenticationManager.authenticate(
                     new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
             );
             if (authentication.isAuthenticated()) {
                 JwtResponse jwtResponse = jwtUtil.generateToken(loginRequest.getUsername());
                 return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
             } else {
                 throw new BadRequestException(UserConstants.USER_NOT_VALID, HttpStatus.BAD_REQUEST);
             }
         }catch (BadCredentialsException ex){
             log.error("Bad User Credentials");
             throw new BadRequestException(UserConstants.BAD_USER_CREDENTIALS, HttpStatus.BAD_REQUEST);
         }

    }
}
