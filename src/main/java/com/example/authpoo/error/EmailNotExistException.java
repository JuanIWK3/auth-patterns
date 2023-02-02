package com.example.authpoo.error;

public class EmailNotExistException extends Exception {
    public EmailNotExistException(String email) {
        super(String.format("Email %s does not exist.", email));
    }
}
