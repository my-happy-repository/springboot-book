package com.study.security.controller.dto;

import com.study.security.service.dto.AddUserDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserRequest {
    private String email;
    private String password;

    public AddUserDto toAddUserDto() {
        return AddUserDto.builder()
                .email(email)
                .password(password)
                .build();
    }
}
