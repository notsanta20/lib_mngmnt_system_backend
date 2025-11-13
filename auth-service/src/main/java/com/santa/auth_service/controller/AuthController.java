package com.santa.auth_service.controller;

import com.santa.auth_service.dto.UserDTO;
import com.santa.auth_service.dto.UserLoginDTO;
import com.santa.auth_service.dto.UserRegisterDTO;
import com.santa.auth_service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserRegisterDTO user) {
        UserDTO newUser = service.registerUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserLoginDTO user){
        String token = service.loginUser(user);

        if(token.equals("false")){
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }else {
            return new ResponseEntity<>(token, HttpStatus.OK);
        }
    }
}
