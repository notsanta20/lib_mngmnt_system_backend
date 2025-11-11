package com.santa.auth_service.dto;

import lombok.Data;

@Data
public class UserLoginDTO {
    private String username;
    private String password;
}
