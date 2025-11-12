package com.santa.auth_service.controller;

import com.santa.auth_service.dto.UserDTO;
import com.santa.auth_service.dto.UserLoginDTO;
import com.santa.auth_service.dto.UserRegisterDTO;
import com.santa.auth_service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> getUsers(@RequestBody UserRegisterDTO user){
        UserDTO newUser = service.registerUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> getUserByUsername(@RequestBody UserLoginDTO user){

        return new ResponseEntity<>("logged in", HttpStatus.OK);
    }
}
