package com.example.authpoo.error;

public class EmailNotFoundException extends Exception{
    public EmailNotFoundException(String email) {
        super(String.format("Email %s not found in the system.", email));
    }
}
