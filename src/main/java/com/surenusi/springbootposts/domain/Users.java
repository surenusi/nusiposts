package com.surenusi.springbootposts.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Users extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 12)
    private String login;

    @Column(length = 60)
    private String password;

    @Column(length = 20)
    private String nickname;

    @Column(unique = true)
    private String email;

    @Column
    private boolean activated;

    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;

    @Builder
    public Users(String login, String password, String nickname, String email,
                 boolean activated, Set<Authority> authorities) {
        this.login = login;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.activated = activated;
        this.authorities = authorities;
    }

    //유저 정보 수정 비즈니스 로직
    public void update(String password, String nickname, String email) {
        this.password = password;
        this.nickname = nickname;
        this.email = email;
    }
}