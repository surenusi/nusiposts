package com.surenusi.springbootposts.service;

import com.surenusi.springbootposts.domain.Users;
import com.surenusi.springbootposts.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {
        return usersRepository.findOneWithAuthoritiesByLogin(login)
                .map(users -> createUser(login, users))
                .orElseThrow(() -> new UsernameNotFoundException(login + " -> 데이터베이스에서 찾을 수 없습니다."));
    }

    private org.springframework.security.core.userdetails.User createUser(String login, Users users) {
        if(!users.isActivated()) {
            throw new RuntimeException(login + " -> 활성화 되어 있지 않습니다.");
        }

        List<GrantedAuthority> grantedAuthorities = users.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(users.getLogin(),
                users.getPassword(),
                grantedAuthorities);
    }
}
