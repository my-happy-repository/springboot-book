package com.study.security.service;

import com.study.security.domain.User;
import com.study.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    // 사용자 이름으로 사용자의 정보를 가져오는 메서드
    // 스프링 시큐리티에서 사용자의 정보를 가져오는 UserDetailService 인터페이스를 구현, 필수로 구현해야 하는 loadByUserName() 메서드를 오버라이딩
    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Email is wrong " + email));
    }
}
