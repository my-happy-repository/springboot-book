package com.study.blog.controller;

import com.study.blog.controller.dto.AddArticleRequest;
import com.study.blog.controller.dto.ArticleResponse;
import com.study.blog.domain.Article;
import com.study.blog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController // HTTP Response Body 객체 데이터를 JSON 형식으로 반환 하는 컨트롤러
public class BlogApiController {

    private final BlogService blogService;

    // HTTP 메서드가 POST 일 때 전달 받은 URL 과 동일하면 메서드로 맵핑
    @PostMapping("/api/articles")
    // @RequestBody 로 요청 본문 값 매핑
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
        Article savedArticle = blogService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
    }

    // 블로그 글 전체 조회 API
    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();

        return ResponseEntity.ok().body(articles);
    }

    // 블로그 글 단건 조회 API
    @GetMapping("/api/articles/{id}")
    public Article findById(@PathVariable(name = "id") Long id) {
        return blogService.findById(id);
    }
}
