package com.example.authpoo.error;

public class ConcreateException extends BaseException{
    public ConcreateException(String message){
        super(message);
    }

    @Override
    public void handleException(){
        System.err.println("Exception: "+ getMessage());
    }
}
