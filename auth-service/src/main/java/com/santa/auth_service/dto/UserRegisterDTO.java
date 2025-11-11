package com.santa.auth_service.dto;

import com.santa.auth_service.model.User;
import lombok.Data;

@Data
public class UserRegisterDTO {
    private String username;
    private String password;
    private String email;
    private long phone;
    private String membershipType;
}
