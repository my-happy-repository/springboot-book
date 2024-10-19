package com.study.springbootbook.contorller;

import com.study.springbootbook.repository.Member;
import com.study.springbootbook.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping("test")
    public List<Member> test(){
        return testService.findAll();
    }
}

