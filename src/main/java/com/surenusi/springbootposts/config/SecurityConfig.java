package com.surenusi.springbootposts.config;

import com.surenusi.springbootposts.jwt.JwtAccessDeniedHandler;
import com.surenusi.springbootposts.jwt.JwtAuthenticationEntryPoint;
import com.surenusi.springbootposts.jwt.JwtSercurityConfig;
import com.surenusi.springbootposts.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .antMatchers("/js/**")      //정적리소스
                .antMatchers("/css/**")
                .antMatchers("/favicon.ico");
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()

                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

//                .and()
//                .headers()
//                .frameOptions()
//                .sameOrigin()

                //세션 설정
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                //권한 설정
                .and()
                .authorizeRequests()
                //허용url
                .antMatchers("/").permitAll()           //홈페이지
                .antMatchers("/post/**").permitAll()    //post api
                .antMatchers("/sign-in").permitAll()    //로그인 api
                .antMatchers("/user/loginCheck/**",
                        "/user/emailCheck/**").permitAll() //유저 아이디, 이메일 중복체크
                .antMatchers("/user").permitAll()       //회원가입 api
                .antMatchers("/user/info").access("hasAnyRole('ADMIN','USER')")
                .antMatchers("/user/info/**").access("hasRole('ADMIN')")
                .anyRequest().authenticated()

                .and()
                .apply(new JwtSercurityConfig(tokenProvider));

        return http.build();
    }
}
