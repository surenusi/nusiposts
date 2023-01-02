package com.surenusi.springbootposts.repository;

import com.surenusi.springbootposts.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.stream.Stream;

public interface PostsRepository extends JpaRepository<Posts, Long> {

    //게시판 전체 조회
    Stream<Posts> findByOrderByIdDesc();

}