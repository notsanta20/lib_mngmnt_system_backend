package com.santa.auth_service.service;

import com.santa.auth_service.dto.UserDTO;
import com.santa.auth_service.exception.UserNotFoundException;
import com.santa.auth_service.model.User;
import com.santa.auth_service.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

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
