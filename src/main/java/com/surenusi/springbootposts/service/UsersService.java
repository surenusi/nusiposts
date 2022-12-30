package com.surenusi.springbootposts.service;

import com.surenusi.springbootposts.domain.Users;
import com.surenusi.springbootposts.dto.user.UsersInfoResponseDto;
import com.surenusi.springbootposts.dto.user.UsersSaveRequestDto;
import com.surenusi.springbootposts.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;

    @Transactional
    public Long createUsers(UsersSaveRequestDto requestDto) { return usersRepository.save(requestDto.toEntity()).getId(); }
}
