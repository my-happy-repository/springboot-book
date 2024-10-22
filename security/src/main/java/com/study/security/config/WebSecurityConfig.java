package com.study.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final UserDetailsService userDetailsService;

    // 1. 해당 requestMatch 시 ignore
    // 인증/인가를 모든 곳에 적용하지는 않음, 일반적으로 정적 리소시에만 설정
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers(toH2Console())
                .requestMatchers("/static/**");
    }

    // 2. 특정 HTTP 요청에 대한 웹 기반 보안 구성
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests((auth) -> auth
                        // permitAll() 누구나 접근이 가능하게 설정, 즉 해당 URL 로 접근 시 인증,인가 필요 없음
                        .requestMatchers("/login", "/signup", "/user").permitAll()
                        // 위에서 설정 한 url 이외의 요청에 대하여 설정
                        .anyRequest()
                        // 별도의 인가는 필요하지 않지만 인증이 성공 된 상태여야 접근이 가능
                        .authenticated()
                )
                .formLogin((form) -> form
                        // 로그인 페이지로 경로를 설정
                        .loginPage("/login")
                        // 로그인이 완료 되었을 때 이동할 경로를 설정
                        .defaultSuccessUrl("/articles")
                )
                .logout((logout) -> logout
                        // 로그아웃이 완료 되었을 때 이동할 경로를 설정
                        .logoutSuccessUrl("/login")
                        // 로그아웃 이후에 세션을 전체 삭제할지 여부를 설정
                        .invalidateHttpSession(true)
                )
                .csrf(AbstractHttpConfigurer::disable)  // csrf 비활성화
                .build();
    }

    // 인증 관리자 관련 설정
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        // 비밀 번호를 암호화 하기 위한 인코더를 설정
        return new BCryptPasswordEncoder();
    }

}

