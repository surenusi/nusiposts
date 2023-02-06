package com.surenusi.springbootposts.repository;

import com.surenusi.springbootposts.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    //유저 아이디 중복 체크
    public int countByLogin(String login);

    //유저 이메일 중복 체크
    public int countByEmail(String email);

    //유저 로그인
    public Optional<Users> findByLogin(String login);
}