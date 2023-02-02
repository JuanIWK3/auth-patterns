package com.example.authpoo.error;

public class EmailExistException extends Exception {
    public EmailExistException(String email) {
        super(String.format("Email %s already exists in the system.", email));
    }
}
