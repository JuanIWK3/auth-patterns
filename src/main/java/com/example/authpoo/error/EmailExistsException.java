package com.example.authpoo.error;

public class EmailExistsException extends Exception{
    public EmailExistsException(String email){
        super(String.format("Email %s already exists.", email));
    }
}
