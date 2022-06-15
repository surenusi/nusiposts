package com.surenusi.springbootposts.repository;

import com.surenusi.springbootposts.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
}