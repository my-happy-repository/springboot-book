package com.study.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserViewController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String siginUp() {
        return "signup";
    }

    @GetMapping("/articles")
    public String articles() {
        return "articles";
    }
}
