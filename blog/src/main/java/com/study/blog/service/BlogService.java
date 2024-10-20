package com.study.blog.service;

import com.study.blog.controller.dto.AddArticleRequest;
import com.study.blog.domain.Article;
import com.study.blog.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor    // final 이 붙거나 @NotNull 이 붙은 필드의 생성자 추가
@Service    // Servlet Container 에 Bean 으로 등록
public class BlogService {

    private final BlogRepository blogRepository;

    // Blog 글 추가 메서드
    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    public Article findById(Long id) {
        return blogRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Not Found Id : " + id)
        );
    }

}
