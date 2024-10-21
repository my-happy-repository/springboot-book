package com.study.security.repository;

import com.study.security.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // 사용자 정보를 가져오기 위해서는 스프링 시큐리티가 이메일을 전달받아야 함
    Optional<User> findByEmail(String email);

}
