package com.study.blog.repository;

import com.study.blog.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository 를 상속 받을 때 엔티티 Article 과 엔티티의 PK 타입 Long 을 인수로 넣음
// JpaRepository 는 CrudRepository 를 상속 함
public interface BlogRepository extends JpaRepository<Article, Long> {

}
