package com.study.security.config.jwt;

import com.study.security.config.JwtProperties;
import com.study.security.config.TokenProvider;
import com.study.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TokenProviderTest {

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    JwtProperties jwtProperties;

}
