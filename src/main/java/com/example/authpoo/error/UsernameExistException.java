package com.example.authpoo.error;

public class UsernameExistException extends Exception{
    public UsernameExistException(String username) {
        super(String.format("Email %s already exists in the system.", username));
    }
}
