package com.study.springbootbook.javal;

import org.junit.jupiter.api.*;

public class JUnitCycleTest {

    @BeforeAll // 전체 테스트를 시작하기 전에 1회 실행 하므로 메서드는 static 으로 선언
    static void beforeAll() {
        System.out.println("@BeforeAll Method !");
    }

    @BeforeEach // 테스트 케이스를 시작하기 전마다 실행
    void beforeEach() {
        System.out.println("Before Each Method !!");
    }

    @Test
    public void test1() {
        System.out.println("Test 1 !!");
    }

    @Test
    public void test2() {
        System.out.println("Test 2 !!");
    }

    @Test
    public void test3() {
        System.out.println("Test 3 !!");
    }

    @AfterAll   // 전체 테스트를 마치고 종료하기 전에 1회 실행  하므로 static
    static void afterAll() {
        System.out.println("@AfterAll Method !!!");
    }

    @AfterEach  // 테스트 케이스를 종료하기 전마다 실행
    public void afterEach() {
        System.out.println("@AfterEach Method !!!");
    }
}
