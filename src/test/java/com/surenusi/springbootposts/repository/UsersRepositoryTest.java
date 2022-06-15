package com.surenusi.springbootposts.repository;

import com.surenusi.springbootposts.domain.Users;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@EnableJpaAuditing
public class UsersRepositoryTest {

    @Autowired
    UsersRepository usersRepository;

    //테스트 초기화 작업
    public void cleanup() {
        usersRepository.deleteAll();
    }

    @Test
    public void userRepositorySaveAndLoad() {
        //given
        cleanup();
        usersRepository.save(Users.builder()
                .login("테스트 유저")
                .password("테스트 비밀번호")
                .nickname("테스트 닉네임")
                .email("테스트유저@gmail.com")
                .build());
        //when
        List<Users> usersList = usersRepository.findAll();
        //then
        Users users = usersList.get(0);
        Assertions.assertThat(users.getId()).isEqualTo(1L);
        Assertions.assertThat(users.getLogin()).isEqualTo("테스트 유저");
        Assertions.assertThat(users.getPassword()).isEqualTo("테스트 비밀번호");
        Assertions.assertThat(users.getNickname()).isEqualTo("테스트 닉네임");
        Assertions.assertThat(users.getEmail()).isEqualTo("테스트유저@gmail.com");
        Assertions.assertThat(users.getCreatedDate()).isBefore(LocalDateTime.now());
        Assertions.assertThat(users.getModifiedDate()).isBefore(LocalDateTime.now());
    }
}
