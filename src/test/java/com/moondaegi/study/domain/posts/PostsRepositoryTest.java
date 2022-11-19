package com.moondaegi.study.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    @After  // 모든 test 후에 실행되는 메소드
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        String title = "게시글 테스트";
        String content = "테스트 내용";

        // 게시글 저장
        postsRepository.save(Posts.builder()
                .title(title).content(content).author("test@mail.com").build());

        List<Posts> postsList = postsRepository.findAll();  // 전체 검색

        Posts posts = postsList.get(0);  // 첫번째 글 가져오기
        assertThat(posts.getTitle()).isEqualTo(title);  // 제목이 일치하는지 확인
        assertThat(posts.getContent()).isEqualTo(content);  // 내용이 일치하는지 확인
    }

    @Test
    public void BaseTimeEntity_등록확인() throws Exception {
        LocalDateTime now = LocalDateTime.of(2022,11,20,0,0,0);
        postsRepository.save(Posts.builder()
                .title("테스트 게시글").content("테스트 내용").author("tester")
                .build());

        List<Posts> postsList = postsRepository.findAll();

        Posts post = postsList.get(0);

        assertThat(post.getCreatedDate()).isAfter(now);
        assertThat(post.getModifiedDate()).isAfter(now);
    }
}
