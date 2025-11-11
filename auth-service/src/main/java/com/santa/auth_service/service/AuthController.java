package com.santa.auth_service.service;

import com.santa.auth_service.dto.UserDTO;
import com.santa.auth_service.dto.UserLoginDTO;
import com.santa.auth_service.dto.UserRegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private UserService service;

    @PostMapping("/users/register")
    public ResponseEntity<UserDTO> addUser(@RequestBody UserRegisterDTO user){
        UserDTO newUser = service.registerUser(user);

        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @PostMapping("/users/login")
    public ResponseEntity<String> loginUser(@RequestBody UserLoginDTO user){
        String userLogin = service.loginUser(user);

        if(userLogin.equals("Login Failed")){
            return new ResponseEntity<>(userLogin, HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(userLogin, HttpStatus.OK);
    }
}
