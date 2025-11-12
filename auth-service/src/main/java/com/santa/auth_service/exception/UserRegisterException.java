package com.santa.auth_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserRegisterException extends RuntimeException{
    public UserRegisterException(){
        super("Error while creating new user, try again");
    }
}
