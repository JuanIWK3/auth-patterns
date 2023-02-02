package com.example.authpoo.error;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){
        super(message);
    }
}
