package com.example.authpoo.error;

public class IdExistException extends Exception{
    public IdExistException(Integer id){
        super(String.format("user id %s does not exist.",id));
    }
}
