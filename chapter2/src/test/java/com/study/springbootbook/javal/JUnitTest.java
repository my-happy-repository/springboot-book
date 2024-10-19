package com.study.springbootbook.javal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JUnitTest {

    // 성공 케이스
    @DisplayName("1 + 2 는 3 이다.")
    @Test
    public void junitTest() {
        final int a = 1;
        final int b = 2;

        final int sum = a + b;
        // assertEquals() 메서드에서 천 번째 인수에는 기대하는 값, 두 번째 인수에는 실제로 검증할 값
        Assertions.assertEquals(sum, a + b);
    }

    // 실패 케이스
    // 성공 케이스
    @DisplayName("1 + 2 는 3 이다.")
    @Test
    public void junitTestFail() {
        final int a = 1;
        final int b = 2;

        final int sum = a + b;
        // assertEquals() 메서드에서 천 번째 인수에는 기대하는 값, 두 번째 인수에는 실제로 검증할 값
        Assertions.assertEquals(sum, 7);
    }
}
