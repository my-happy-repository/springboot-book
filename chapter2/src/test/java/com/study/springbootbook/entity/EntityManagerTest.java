package com.study.springbootbook.entity;

import com.study.springbootbook.repository.Member;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EntityManagerTest {

    @Autowired
    EntityManager entityManager;

    public void example() {

        // 1. 엔티티 매니저가 엔티티를 관리하지 않는 상태 (비 영속 상태)
        Member member = new Member(1L, "홍길동");

        // 2. 엔티티가 관리 되는 상태
        entityManager.persist(member);

        // 3. 엔티티 객체가 분리 된 상태
        entityManager.detach(member);

        // 4. 엔티티 객체가 삭제 된 상태
        entityManager.remove(member);

    }
}
