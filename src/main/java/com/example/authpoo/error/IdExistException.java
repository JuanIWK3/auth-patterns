package com.example.authpoo.error;

public class IdExistException extends Exception{
    public IdExistException(Integer id){
        super(String.format("Employee with id %s already exists in the system.",id));
    }
}
