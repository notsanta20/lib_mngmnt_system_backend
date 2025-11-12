package com.santa.auth_service.service;

import com.santa.auth_service.dto.UserDTO;
import com.santa.auth_service.dto.UserLoginDTO;
import com.santa.auth_service.dto.UserRegisterDTO;
import com.santa.auth_service.exception.UserAlreadyExistsException;
import com.santa.auth_service.model.Member;
import com.santa.auth_service.model.User;
import com.santa.auth_service.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepo repo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public UserDTO registerUser(UserRegisterDTO user){
        Optional<User> checkUser = repo.findByUsername(user.getUsername());

        if(checkUser.isPresent()){
            throw new UserAlreadyExistsException(user.getUsername());
        }

        Member member = Member.builder()
                .name(user.getUsername())
                .email(user.getEmail())
                .phone(user.getPhone())
                .membershipType(user.getMembershipType())
                .membershipDate(LocalDate.now())
                .expiryDate(LocalDate.now())
                .totalFines(0.0)
                .isActive(true)
                .build();

        User newUser = User.builder()
                .username(user.getUsername())
                .password(encoder.encode(user.getPassword()))
                .build();

        User registeredUser = repo.save(newUser);

        return new UserDTO(registeredUser);
    }

    public UserDTO loginUser(UserLoginDTO user) {


        return new UserDTO(new User());
    }
}
