package com.study.security.service;

import com.study.security.domain.User;
import com.study.security.repository.UserRepository;
import com.study.security.service.dto.AddUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUserDto dto) {
       return userRepository.save(
               User.builder()
                       .email(dto.getEmail())
                        // Password 암호화
                       .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                       .build()
            ).getId();
    }
}
