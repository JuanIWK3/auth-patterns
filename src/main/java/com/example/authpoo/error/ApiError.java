package com.example.authpoo.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class ApiError {
    int status;
    String message;
    long timestamp;
    String path;

    public ApiError(int status, String message, String path){
        super();
        this.status = status;
        this.message = message;
        this.path = path;
        this.timestamp = new Date().getTime();
    }

}