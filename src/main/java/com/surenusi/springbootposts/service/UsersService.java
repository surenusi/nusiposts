package com.surenusi.springbootposts.service;

import com.surenusi.springbootposts.common.SecurityUtil;
import com.surenusi.springbootposts.domain.Authority;
import com.surenusi.springbootposts.domain.Users;
import com.surenusi.springbootposts.dto.user.UsersSaveRequestDto;
import com.surenusi.springbootposts.dto.user.UsersUpdateRequestDto;
import com.surenusi.springbootposts.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    //회원가입
    @Transactional
    public Long createUsers(UsersSaveRequestDto requestDto) {
        //회원 중복 여부 체크
        if(usersRepository.findByLogin(requestDto.getLogin()).orElse(null) != null) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }

        //회원 권한 설정
        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        Users users = Users.builder()
                .login(requestDto.getLogin())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .nickname(requestDto.getNickname())
                .email(requestDto.getEmail())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        return usersRepository.save(users).getId();
    }

    //유저 아이디, 이메일 중복체크
    @Transactional(readOnly = true)
    public int countUsers(String column, String data) {
        if(column == "login") {
            return usersRepository.countByLogin(data);
        }
        else if(column == "email") {
            return usersRepository.countByEmail(data);
        }

        return -1;
    }

    //유저 정보 수정
    @Transactional
    public Long updateUsers(Long userId, UsersUpdateRequestDto requestDto) {
        Users users = usersRepository.findById(userId).get();
        users.update(requestDto.getPassword(), requestDto.getNickname(), requestDto.getEmail());

        return users.getId();
    }

    //유저 삭제
    @Transactional
    public void deleteUsers(Long userId) {
        usersRepository.deleteById(userId);

        return;
    }

    //유저, 권한 정보 조회(관리자)
    @Transactional(readOnly = true)
    public Optional<Users> getUsersWithAuthorities(String login) {
        return usersRepository.findOneWithAuthoritiesByLogin(login);
    }

    //유저, 권한 정보 조회
    @Transactional(readOnly = true)
    public Optional<Users> getMyUsersWithAuthorities() {
        return SecurityUtil.getCurrentUsername().flatMap(usersRepository::findOneWithAuthoritiesByLogin);
    }
}
