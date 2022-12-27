package com.surenusi.springbootposts;

import com.surenusi.springbootposts.domain.Posts;
import com.surenusi.springbootposts.dto.posts.PostsSaveRequestDto;
import com.surenusi.springbootposts.dto.posts.PostsUpdateRequestDto;
import com.surenusi.springbootposts.repository.PostsRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @AfterEach
    public void cleanup() throws Exception {
        postsRepository.deleteAll();
    }

    @Test
    public void insertPosts() throws Exception {
        //given
        PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
                .title("테스트제목")
                .content("테스트내용")
                .author("테스트작성자")
                .build();
        //when
        ResponseEntity<Long> responseEntity =
                restTemplate.postForEntity("http://localhost:" + port + "/createPost", dto, Long.class);
        //then
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> posts = postsRepository.findAll();
        Assertions.assertThat(posts.get(0).getTitle()).isEqualTo("테스트제목");
        Assertions.assertThat(posts.get(0).getContent()).isEqualTo("테스트내용");
        Assertions.assertThat(posts.get(0).getAuthor()).isEqualTo("테스트작성자");
    }

    @Test
    public void updatePosts() throws Exception {
        //given
        Posts savedPosts = postsRepository
                .save(Posts.builder()
                        .title("테스트제목")
                        .content("테스트내용")
                        .author("테스트작성자")
                        .build());

        Long updateId = savedPosts.getId();
        String updateTitle = "테스트제목2";
        String updateContent = "테스트내용2";

        PostsUpdateRequestDto dto = PostsUpdateRequestDto.builder()
                .title(updateTitle)
                .content(updateContent)
                .build();

        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(dto);
        //when
        ResponseEntity<Long> responseEntity =
                restTemplate
                        .exchange("http://localhost:" + port + "/post/" + updateId
                                , HttpMethod.PUT, requestEntity, Long.class );
        //then
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> posts = postsRepository.findAll();
        Assertions.assertThat(posts.get(0).getTitle()).isEqualTo(updateTitle);
        Assertions.assertThat(posts.get(0).getContent()).isEqualTo(updateContent);
    }

    @Test
    public void deletePost() throws Exception {
        //given
        Posts post = postsRepository
                .save(Posts.builder()
                        .title("테스트제목")
                        .content("테스트내용")
                        .author("테스트작성자")
                        .build());
        Long deleteId = post.getId();
        HttpEntity<Posts> requestEntity = new HttpEntity<>(post);
        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange("http://localhost:" + port + "/post/" + deleteId,
                HttpMethod.DELETE, requestEntity, Long.class);
        //then
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        List<Posts> posts = postsRepository.findAll();
        Assertions.assertThat(posts.size()).isEqualTo(0);
    }

}
