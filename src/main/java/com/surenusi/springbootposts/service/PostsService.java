package com.surenusi.springbootposts.service;

import com.surenusi.springbootposts.domain.Posts;
import com.surenusi.springbootposts.dto.posts.PostsViewResponseDto;
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

    /* 게시글 쓰기 */
    @Transactional
    public Long createPosts(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    /* 게시글 전체 조회 */
    @Transactional(readOnly = true)
    public List<PostsMainResponseDto> readAllPosts() {
        return postsRepository.findByOrderByIdDesc()
                .map(PostsMainResponseDto::new)
                .collect(Collectors.toList());
    }

    /* 게시글 상세 조회 */
    @Transactional(readOnly = true)
    public PostsViewResponseDto readPosts(Long postId) {
        Posts posts = postsRepository.findById(postId).get();
        return new PostsViewResponseDto(posts);
    }

    /* 게시글 수정 */
    @Transactional
    public Long updatePosts(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).get();
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return posts.getId();
    }

    /* 게시글 삭제 */
    @Transactional
    public void deletePosts(Long postId) {
        postsRepository.deleteById(postId);

        return;
    }

    /* 게시글 조회수 증가 */
    @Transactional
    public void updatePostsViewCount(Long postId) {
        Posts posts = postsRepository.findById(postId).get();
        posts.updateViewCount();
        return;
    }
}
