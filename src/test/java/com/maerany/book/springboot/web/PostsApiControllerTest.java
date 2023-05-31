package com.maerany.book.springboot.web;

import com.maerany.book.springboot.domain.posts.Posts;
import com.maerany.book.springboot.domain.posts.PostsRepository;
import com.maerany.book.springboot.web.dto.PostsSaveRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// jpa기능까지 한번에 테스트할 때 사용하는 어노테이션 @SpringBootTest
// @MevMvcTest는 jap기능이 작동하지 않고 controller, controllerAdvice 등 외부 연동과 관련된 부부만 활성화
public class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    // 데이터베이스 초기화함으로써 테스트할 때 오류 방지
    @After
    public void tearDown() throws Exception{
        postsRepository.deleteAll();
    }

    @Test
    public void Posts_등록된다() throws Exception{
        // 여기서 오류가 발생했었는데,
        // 1. PostsApiController에 @PostMapping() 안해서
        // 2. PostsSaveRequestDto에 @Getter / @NoArgsConstructor 안해서..
        // 어노테이션에 대한 사용을 언제, 왜 하는지 확실히 익힐 필요가 있음..!

        //given
        String title = "title";
        String content = "content";
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("author")
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);

    }
}
