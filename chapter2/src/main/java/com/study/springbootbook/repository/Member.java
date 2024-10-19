package com.study.springbootbook.repository;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자
@AllArgsConstructor
@Getter
@Entity // 엔티티로 지정
public class Member {

    // Id
    // GeneratedValue 전략
    // AUTO : 선택한 데이터 베이스 dialect 에 따라 방식을 자동으로 선택 (기본 값)
    // IDENTITY : 기본 키 생성을 데이터 베이스에 위임 (= AUTOINCREMENT)
    // SEQUENCE : 데이터 베이스 시퀀스를 사용하여 기본키를 할당하는 방법, 오라클에서 주로 사용
    // TABLE : 키 생성 테이블을 사용
    @Id // id 필드를 기본키로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // 기본 키를 1씩 증가
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "name", nullable = false)    // name 이라는 not null 컬럼과 매핑
    private String name;

}
