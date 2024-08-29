package com.demoJWT.service;

import com.demoJWT.constant.UserConstants;
import com.demoJWT.exception.EntityNotFoundException;
import com.demoJWT.model.MyUserDetails;
import com.demoJWT.model.User;
import com.demoJWT.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new EntityNotFoundException(UserConstants.USER_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        return new MyUserDetails(user);
    }
}
