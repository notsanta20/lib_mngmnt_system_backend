package com.santa.auth_service.service;

import com.santa.auth_service.dto.UserDTO;
import com.santa.auth_service.model.User;
import com.santa.auth_service.repo.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    UserRepo userRepo;

    @InjectMocks
    UserService userService;

    User mockUser = new User(1,"santa","1234",1);

    @Test
    void shouldGetAllUsers(){
        List<User> userList = List.of(mockUser);
        Pageable pageable = PageRequest.of(0,10);
        Page<User> mockPage = new PageImpl<>(userList, pageable, userList.size());

        when(userRepo.findAll(pageable)).thenReturn(mockPage);
        Page<UserDTO> userDTOPage = userService.getAllUsers(0,10);

        assertEquals(mockUser.getUsername(), userDTOPage.getContent().getFirst().getUsername());
    }

    @Test
    void shouldGetUserByUsername(){
        when(userRepo.findByUsername("santa")).thenReturn(Optional.ofNullable(mockUser));
        UserDTO user = userService.getUserByUsername("santa");

        assertEquals(mockUser.getId(), user.getId());
    }

}
