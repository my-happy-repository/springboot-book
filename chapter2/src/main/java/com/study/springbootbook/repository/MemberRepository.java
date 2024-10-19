package com.study.springbootbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// DB 에서 데이터를 가져오는 Persistence 계층
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

}
