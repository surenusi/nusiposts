package com.surenusi.springbootposts.service;

import com.surenusi.springbootposts.domain.Users;
import com.surenusi.springbootposts.dto.user.UsersInfoResponseDto;
import com.surenusi.springbootposts.dto.user.UsersSaveRequestDto;
import com.surenusi.springbootposts.dto.user.UsersUpdateRequestDto;
import com.surenusi.springbootposts.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;

    //회원가입
    @Transactional
    public Long createUsers(UsersSaveRequestDto requestDto) { return usersRepository.save(requestDto.toEntity()).getId(); }

    //유저 정보 조회
    @Transactional(readOnly = true)
    public UsersInfoResponseDto readUsers(Long userId) {
        return new UsersInfoResponseDto(usersRepository.findById(userId).get());
    }

    //유저 아이디 중복 체크
    @Transactional(readOnly = true)
    public int countUsers(String userLogin) {
        return usersRepository.countByLogin(userLogin);
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
}
