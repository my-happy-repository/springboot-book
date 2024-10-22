package com.study.security.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
public class AddUserDto {
    private String email;
    private String password;

    public AddUserDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}

