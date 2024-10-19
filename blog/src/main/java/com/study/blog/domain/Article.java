package com.study.blog.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity // 엔티티로 지정
@Getter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키를 자동으로 1씩 증가
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    // Builder 패턴은 롬복에서 지원하는 어노테이션 생성자 위에 입력 하면 패턴 방식으로 객체를 생성할 수 있음
    // 빌더 패턴 사용 시 객체를 유연하고 직관적으로 사용이 가능, 빌더 패턴 사용 시 어느 필드에 어떤 값이 들어가는 지 명시적으로 알 수 있음
    @Builder    // 빌더 패턴으로 객체를 생성
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    protected Article() {
    }
}