package com.example.authpoo.error;

public class IdNotFoundException extends Exception {
    public IdNotFoundException(Integer id){
        super(String.format("User id %s not found in the system.",id));
    }
}
