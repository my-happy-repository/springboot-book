package com.study.blog.controller;

import com.study.blog.controller.dto.AddArticleRequest;
import com.study.blog.domain.Article;
import com.study.blog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController // HTTP Response Body 객체 데이터를 JSON 형식으로 반환 하는 컨트롤러
public class BlogController {

    private final BlogService blogService;

    // HTTP 메서드가 POST 일 때 전달 받은 URL 과 동일하면 메서드로 맵핑
    @PostMapping("/api/articles")
    // @RequestBody 로 요청 본문 값 매핑
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
        Article savedArticle = blogService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
    }

}
