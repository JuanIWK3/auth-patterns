package com.example.demo.controller;

import com.example.demo.document.User;
import com.example.demo.dto.SiginupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping ("/api/auth")
public class AuthController {
    @Autowired
    UserDetailsManager userDetailsManager;
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody SiginupDTO siginupDTO){
        User user = new User(siginupDTO.getUsername(), siginupDTO.getPassword());
        userDetailsManager.createUser(user);

        Authentication authentication = UsernamePasswordAuthenticationToken.authenticated(user,
                siginupDTO.getPassword(), Collections.EMPTY_LIST);

        return ResponseEntity.ok();
    }
}
