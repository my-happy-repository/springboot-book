package com.study.springbootbook.contorller;

import com.study.springbootbook.repository.Member;
import com.study.springbootbook.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// SpringBootTest 어노테이션은 메인 어플리케이션 클래스에 추가하는 애노테이션인 @SpringBootApplication 이 있는 클래스를 찾고 그 클래스에 포함 되어 있는 Bean 을 등록
@SpringBootTest  // 테스트용 애플맄에션 컨텍스트 생성
@AutoConfigureMockMvc   // MockMvc 생성 및 자동 구성
class TestControllerTest {

    // MockMvc 는 어플리케이션을 서버에 배포하지 않고 테스트용 MVC 환경을 만들어서 요청 및 전송 응답 기능을 제공하는 유틸리티 클래스, 컨트롤러를 테스트 시 사용
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }

    @AfterEach
    public void cleanUp() {
        memberRepository.deleteAll();
    }

    @DisplayName("getAllMembers : 아티클 조회에 성공한다.")
    @Test
    public void getALlMembers() throws Exception {
        // given 멤버를 저장
        final String url = "/test";
        Member savedMember = memberRepository.save(new Member(1L, "홍길동"));

        // when 멤버 리스트를 조회하는 API 를 호출
        final ResultActions result = mockMvc.perform(get(url).accept(MediaType.APPLICATION_JSON));

        // then 응답 코드가 200 OK 고 반환 받은 값 중에 0 번째 요소의 id 와 name 이 저장 된 값과 같은 지 확인
        result
                .andExpect(status().isOk())
                // 응답의 0번째 값이 DB 에 저장 한 값과 같은 지 확인
                .andExpect(jsonPath("$[0].id").value(savedMember.getId()))
                .andExpect(jsonPath("$[0].name").value(savedMember.getName()));
    }

















}