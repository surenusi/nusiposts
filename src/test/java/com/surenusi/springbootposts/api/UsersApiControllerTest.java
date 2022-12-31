package com.surenusi.springbootposts.api;

import com.surenusi.springbootposts.domain.Users;
import com.surenusi.springbootposts.dto.user.UsersSaveRequestDto;
import com.surenusi.springbootposts.dto.user.UsersUpdateRequestDto;
import com.surenusi.springbootposts.repository.UsersRepository;
import org.apache.coyote.Response;
import org.assertj.core.api.Assert;
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
public class UsersApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UsersRepository usersRepository;

    @AfterEach
    public void cleanup() throws Exception {
        usersRepository.deleteAll();
    }

    @Test
    public void insertUsers() throws Exception {
        //given
        UsersSaveRequestDto requestDto = UsersSaveRequestDto.builder()
                .login("테스트유저")
                .password("테스트비밀번호")
                .nickname("테스트닉네임")
                .email("테스트@test.com")
                .build();
        //when
        ResponseEntity<Long> responseEntity =
                restTemplate.postForEntity("http://localhost:" + port + "/user", requestDto, Long.class);
        //then
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Users> users = usersRepository.findAll();
        Users user = users.get(0);
        Assertions.assertThat(user.getLogin()).isEqualTo("테스트유저");
        Assertions.assertThat(user.getPassword()).isEqualTo("테스트비밀번호");
        Assertions.assertThat(user.getNickname()).isEqualTo("테스트닉네임");
        Assertions.assertThat(user.getEmail()).isEqualTo("테스트@test.com");
    }

    @Test
    public void updateUsers() throws Exception {
        //given
        Users savedUsers = usersRepository
                .save(Users.builder()
                        .login("테스트유저")
                        .password("테스트비밀번호")
                        .nickname("테스트닉네임")
                        .email("테스트@test.com")
                        .build());

        Long updateUserId = savedUsers.getId();
        String updatePassword = "수정된비밀번호";
        String updateNickname = "수정된닉네임";
        String updateEmail = "수정@test.com";

        UsersUpdateRequestDto requestDto = UsersUpdateRequestDto.builder()
                .password("수정된비밀번호")
                .nickname("수정된닉네임")
                .email("수정@test.com")
                .build();

        HttpEntity<UsersUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long> responseEntity =
                restTemplate
                        .exchange("http://localhost:" + port + "/user/" + updateUserId
                                , HttpMethod.PUT, requestEntity, Long.class);
        //then
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).isGreaterThan(0L);

        Users users = usersRepository.findAll().get(0);
        Assertions.assertThat(users.getPassword()).isEqualTo(updatePassword);
        Assertions.assertThat(users.getNickname()).isEqualTo(updateNickname);
        Assertions.assertThat(users.getEmail()).isEqualTo(updateEmail);
    }

    @Test
    public void deleteUsers() throws Exception {
        //given
        Users savedUsers = usersRepository
                .save(Users.builder()
                        .login("테스트유저")
                        .password("테스트비밀번호")
                        .nickname("테스트닉네임")
                        .email("테스트@test.com")
                        .build());
        Long deleteId = savedUsers.getId();
        HttpEntity<Users> requestEntity = new HttpEntity<>(savedUsers);
        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange("http://localhost:" + port + "/user/" + deleteId,
                HttpMethod.DELETE, requestEntity, Long.class);
        //then
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        Assertions.assertThat(usersRepository.findAll().size()).isEqualTo(0);
    }
}
