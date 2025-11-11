package com.santa.auth_service.dto;

import com.santa.auth_service.model.User;
import lombok.Data;

@Data
public class UserDTO {
    private long id;
    private String username;
    private long memberId;

    public UserDTO(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.memberId = user.getMemberId();
    }

}
