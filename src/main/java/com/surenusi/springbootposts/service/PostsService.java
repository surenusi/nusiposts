package com.surenusi.springbootposts.service;

import com.surenusi.springbootposts.domain.Posts;
import com.surenusi.springbootposts.dto.posts.PostViewResponseDto;
import com.surenusi.springbootposts.dto.posts.PostsMainResponseDto;
import com.surenusi.springbootposts.dto.posts.PostsSaveRequestDto;
import com.surenusi.springbootposts.dto.posts.PostsUpdateRequestDto;
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

    @Transactional(readOnly = true)
    public PostViewResponseDto readPost(Long id) {
        return new PostViewResponseDto(postsRepository.findOneById(id));
    }

    @Transactional
    public Long updatePost(Long id, PostsUpdateRequestDto dto) {
        Posts posts = postsRepository.findOneById(id);
        posts.update(dto.getTitle(), dto.getContent());

        return posts.getId();
    }

    @Transactional
    public void deletePost(Long id) {
        postsRepository.deleteById(id);
        return;
    }
}
