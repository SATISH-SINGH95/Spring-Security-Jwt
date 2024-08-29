package com.demoJWT.controller;

import com.demoJWT.model.User;
import com.demoJWT.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserService userService;

    @PostMapping("/do-register")
    public ResponseEntity<String> register(@RequestBody User user){
        userService.register(user);
        return new ResponseEntity<>("User registered successfully !", HttpStatus.CREATED);
    }
}
