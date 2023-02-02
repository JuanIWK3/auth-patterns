package com.example.authpoo.error;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String username){
        super(String.format("Employee with username %s not found in the system.",username));
    }
}
