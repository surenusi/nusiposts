package com.surenusi.springbootposts.service;

import com.surenusi.springbootposts.dto.posts.PostsMainResponseDto;
import com.surenusi.springbootposts.dto.posts.PostsSaveRequestDto;
import com.surenusi.springbootposts.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long createPost(PostsSaveRequestDto dto) {
        return postsRepository.save(dto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public List<PostsMainResponseDto> readAllPosts() {
        return postsRepository.findByOrderByIdDesc()
                .map(PostsMainResponseDto::new)
                .collect(Collectors.toList());
    }
}
