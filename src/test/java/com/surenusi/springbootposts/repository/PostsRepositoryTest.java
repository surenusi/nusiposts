package com.surenusi.springbootposts.repository;

import org.assertj.core.api.Assertions;
import com.surenusi.springbootposts.domain.Posts;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@EnableJpaAuditing
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    //테스트 초기화 작업
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void postsSaveAndLoad() {
        //given
        cleanup();
        postsRepository.save(Posts.builder()
                .title("테스트 제목")
                .content("테스트 내용")
                .author("테스트 작성자")
                .build());
        //when
        List<Posts> postsList = postsRepository.findAll();
        //then
        Posts posts = postsList.get(0);
        Assertions.assertThat(posts.getId()).isEqualTo(1L);
        Assertions.assertThat(posts.getTitle()).isEqualTo("테스트 제목");
        Assertions.assertThat(posts.getContent()).isEqualTo("테스트 내용");
        Assertions.assertThat(posts.getAuthor()).isEqualTo("테스트 작성자");
        Assertions.assertThat(posts.getCreatedDate()).isBefore(LocalDateTime.now());
        Assertions.assertThat(posts.getModifiedDate()).isBefore(LocalDateTime.now());
    }
}
