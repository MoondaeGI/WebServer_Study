package com.moondaegi.study.web;

import com.moondaegi.study.domain.posts.Posts;
import com.moondaegi.study.domain.posts.PostsRepository;
import com.moondaegi.study.web.dto.PostsResponseDTO;
import com.moondaegi.study.web.dto.PostsSaveRequestDTO;
import com.moondaegi.study.web.dto.PostsUpdateRequestDTO;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {
    @LocalServerPort
    private int port;  // 랜덤 포트값을 받아올 변수

    @Autowired
    private TestRestTemplate testRestTemplate;  // JPA는 WebMvcTest 사용 불가

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void teardown() throws Exception {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글_등록하기() throws Exception {
        String title = "테스트 게시글";
        String content = "테스트 내용";

        // 게시글 등록
        PostsSaveRequestDTO postsSaveRequestDTO = PostsSaveRequestDTO.builder()
                .title(title).content(content).author("tester")
                .build();

        // 랜덤 포트 번호의 url 생성
        String url = "http://localhost:" + port + "/api/posts";

        // 해당 url로 DTO 실행
        ResponseEntity<Long> responseEntity =
                testRestTemplate.postForEntity(url, postsSaveRequestDTO, Long.class);

        // 스테이터스 코드 및 실행값 확인
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();  // 사용된 값 가져오기
        assertThat(all.get(0).getTitle()).isEqualTo(title);  // 테스트 게시글과 비교
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    @Test
    public void 게시글_수정하기() throws Exception {
        Posts testPost = postsRepository.save(Posts.builder()
                .title("테스트 타이틀").content("테스트 내용").author("tester")
                .build());

        Long updateId = testPost.getId();
        String updateTitle = "수정된 타이틀";
        String updateContent = "수정된 내용";

        PostsUpdateRequestDTO postsUpdateRequestDTO = PostsUpdateRequestDTO.builder()
                .title(updateTitle).content(updateContent)
                .build();

        String url = "http://localhost:" + port + "/api/posts/" + updateId;

        HttpEntity<PostsUpdateRequestDTO> httpEntity = new HttpEntity<>(postsUpdateRequestDTO);

        ResponseEntity<Long> responseEntity =
                testRestTemplate.exchange(url, HttpMethod.PUT, httpEntity, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(updateTitle);
        assertThat(all.get(0).getContent()).isEqualTo(updateContent);
    }
}
