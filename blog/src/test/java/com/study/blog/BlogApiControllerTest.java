package com.study.blog;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.blog.controller.dto.AddArticleRequest;
import com.study.blog.domain.Article;
import com.study.blog.repository.BlogRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BlogApiControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    // ObjectMapper 클래스 - 자바 객체를 JSON 데이터로  변환하는 직렬화 Serialization 또는 반대로 JSON 데이터를 자바에서 사용하기 위하여
    // 자바 객체로 변환하는 역 직렬화 할 때 사용, ObjectMapper 클래스는 Jackson
    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    BlogRepository blogRepository;

    // 테스트 실행 전 실행하는 메서드
    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext).build();

        blogRepository.deleteAll();
    }

    @DisplayName("addArticle: 블로그 글 추가에 성공한다.")
    @Test
    public void addArticle() throws Exception {
        // given
        final String url = "/api/articles";
        final String title = "title";
        final String content = "content";
        final AddArticleRequest userRequest = new AddArticleRequest(title, content);

        // 객체 JSON 으로 직렬화
        final String requestBody = objectMapper.writeValueAsString(userRequest);

        // when
        // 설정 한 내용을 바탕으로 요청 전송
        ResultActions result = mockMvc
                .perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));

        // then
        // Article 생성에 성공 하여야 한다.
        result.andExpect(status().isCreated());

        List<Article> articles = blogRepository.findAll();

        Assertions.assertThat(articles.size()).isEqualTo(1);    // 크기가 1 인지 검증
        Assertions.assertThat(articles.getFirst().getTitle()).isEqualTo(title);
        Assertions.assertThat(articles.getFirst().getContent()).isEqualTo(content);
    }

    @DisplayName("findALlArticles: 블로그 글 목록 조회에 성공 한다.")
    @Test
    public void findALlArticles() throws Exception {
        // given
        final String url = "/api/articles";
        final String title = "title";
        final String content = "content";

        blogRepository.save(
            Article.builder()
                    .title(title)
                    .content(content)
                    .build()
        );

        // when
        final ResultActions resultActions = mockMvc.perform(get(url).accept(MediaType.APPLICATION_JSON));

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value(content))
                .andExpect(jsonPath("$[0].title").value(title));
    }

    @DisplayName("findArticle: 블로그 글 조회에 성공합니다.")
    @Test
    public void findArticle() throws Exception {
        // given
        final String url = "/api/articles/{id}";
        final String title = "title";
        final String content = "content";

        // when
        Article savedArticle = blogRepository.save(
            Article.builder()
                    .title(title)
                    .content(content)
                    .build()
        );

        // then
        final ResultActions resultActions =
                mockMvc.perform(get(url, savedArticle.getId()).accept(MediaType.APPLICATION_JSON));

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value(content))
                .andExpect(jsonPath("$.title").value(title));

    }
}