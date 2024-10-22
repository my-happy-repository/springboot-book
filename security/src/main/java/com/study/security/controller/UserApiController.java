package com.study.security.controller;

import com.study.security.controller.dto.AddUserRequest;
import com.study.security.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PostMapping("/user")
    public String signUp(AddUserRequest request) {
        userService.save(request.toAddUserDto());   // 회원 가입 메서드 호출

        return "redirect:login";    // 회원 가입이 완료 된 이후에 로그인
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,HttpServletResponse response) {
        // Handler 를 호출하여 로그아웃 처리
        new SecurityContextLogoutHandler().logout(request, response,
                SecurityContextHolder.getContext().getAuthentication());

        return "redirect:login";
    }
}
