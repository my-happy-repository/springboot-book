package com.study.springbootbook.service;

import com.study.springbootbook.repository.Member;
import com.study.springbootbook.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {

    private final MemberRepository memberRepository;

    public List<Member> findAll() {
        return memberRepository.findAll();
    }
}

