package com.santa.member_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MemberNotFoundException extends RuntimeException{
    public MemberNotFoundException(long id){
        super("Member not found with ID: " + id);
    }
}
