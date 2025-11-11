package com.santa.auth_service.service;

import com.santa.auth_service.dto.UserDTO;
import com.santa.auth_service.dto.UserLoginDTO;
import com.santa.auth_service.dto.UserRegisterDTO;
import com.santa.auth_service.exception.UserAlreadyExistsException;
import com.santa.auth_service.exception.UserNotFoundException;
import com.santa.auth_service.model.User;
import com.santa.auth_service.repo.UserRepo;
import com.santa.auth_service.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public UserDTO registerUser(UserRegisterDTO user) {
        if(repo.findByUsername(user.getUsername()).isPresent()){
            throw new UserAlreadyExistsException(user.getUsername());
        }

        User newUser = User.builder()
                .username(user.getUsername())
                .hash(encoder.encode(user.getPassword()))
                .memberId(1)
                .build();

        User savedUser = repo.save(newUser);

        return new UserDTO(savedUser);
    }

    public String loginUser(UserLoginDTO user) {
        AuthenticationManager authentication = (AuthenticationManager) authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );

        UserDetails userDetails = (UserDetails) ((org.springframework.security.core.Authentication) authentication).getPrincipal();

        return jwtUtil.generateToken(userDetails.getUsername());
    }

    public Page<UserDTO> getAllUsers(int page, int size){
        Pageable userPage = PageRequest.of(page,size);
        Page<User> users = repo.findAll(userPage);

        return users.map(UserDTO::new);
    }

    public UserDTO getUserByUsername(String username) {
        User user = repo.findByUsername(username).orElseThrow(()-> new UserNotFoundException(username));
        return new UserDTO(user);
    }


}
