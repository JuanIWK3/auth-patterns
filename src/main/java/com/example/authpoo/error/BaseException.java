package com.example.authpoo.error;

public abstract class BaseException extends Exception implements ExceptionHandler{

    public BaseException(String message){
        super(message);
    }

}
