package com.sparta.todo.dto.user;

import com.sparta.todo.entity.User;
import lombok.Getter;

@Getter
public class SignupResponseDto {
    private Long userId;
    private String username;
    private String password;


    public SignupResponseDto(User user){
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.password = user.getPassword();
    }
}
