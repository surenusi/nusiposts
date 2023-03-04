# nusiposts
> Spring Boot, REST API, Spring Data JPA, Spring Security, JWT등을 적용해 연습해보기 위해 시작한 프로젝트

# 1.프로젝트 개요

- **프로젝트 명칭** : nusiposts
- **프로젝트 소개** : Spring Boot, REST API, Spring Data JPA, Spring Security, JWT등을 적용해 연습해보기 위해 시작한 프로젝트
- **개발 인원** : 1명
- **개발 기간** : 2022.06.09 ~ 2023.03.04
- **주요 기능**

  - 게시글 CRUD
  - 게시글 조회수 증가
  - 회원가입
  - Spring Security , JWT 기반 로그인 처리
  - Spring Security 필터링을 통한 접근 권한 설정
  
- **백엔드 개발 언어 및 개발 환경**

  - ```java17```
  - ```Windows```
  - ```IntelliJ```
  - ```Spring Boot 2.7.0```
  - ```gradle```
  - ```JPA (Spring Data JPA)```
  - ```Spring Security```
  - ```JJWT```
  
- **프론트 개발 언어 및 개발 환경**

  - ```Visual Stuido Code```
  - ```HTML5```
  - ```CSS3```
  - ```BootStrap 5.2.2```
  - ```Java Script```
  - ```JQuery 3.6.1```
  
- **데이터베이스**
  
  - ```MariaDB```

- **형상관리**
  
  - ```Git```
  
# 2.프로젝트 기능 및 소개

- Spring Boot, REST API, Spring Data JPA, Spring Security, JWT등을 적용해 연습해보기 위해 시작한 프로젝트

## 메인화면 및 게시글 쓰기

![게시글쓰기1](https://user-images.githubusercontent.com/52432049/222877937-aaaf6f8d-4123-4922-a9a1-21064c415a20.png)
![게시글쓰기2](https://user-images.githubusercontent.com/52432049/222877939-10af64e8-27ce-4f38-a4fc-50164a8c6bbe.png)
![메인화면](https://user-images.githubusercontent.com/52432049/222877500-3161c82c-1e3c-4131-9e22-bfc684b0a893.png)

- 사용자는 게시글을 작성할 수 있다.
- 게시글이 유효하지 않은 데이터라면 사용자에게 피드백을 보여준다.

## 게시글 조회

![게시글조회](https://user-images.githubusercontent.com/52432049/222877509-e043156d-d692-404e-bb10-b865da53b782.png)

- 사용자는 게시글을 상세 조회할 수 있다.
- 사용자가 게시글을 조회하면 조회수가 1 오른다.

## 게시글 수정

![게시글수정](https://user-images.githubusercontent.com/52432049/222877516-0f819331-1062-4b28-90b0-c8881e78f589.png)
![게시글수정2](https://user-images.githubusercontent.com/52432049/222877518-65cc6bba-54ca-4c8f-b9e5-227b3fd5a6d0.png)

- 사용자는 게시글을 수정할 수 있다.

## 게시글 삭제

![게시글삭제1](https://user-images.githubusercontent.com/52432049/222877519-8abf5c11-cd2f-40c0-ba9b-dabe60e93704.png)
![게시글삭제2](https://user-images.githubusercontent.com/52432049/222877521-702b5837-110c-42ce-b401-12208a01821b.png)

- 사용자는 게시글을 삭제할 수 있다.

## 회원 가입

![회원가입1](https://user-images.githubusercontent.com/52432049/222877526-2935ecd0-205e-4b20-9317-3cedf96da0f7.png)
![회원가입2](https://user-images.githubusercontent.com/52432049/222877527-1441dd11-4080-4a55-be25-bf616adaff4a.png)
![회원가입3](https://user-images.githubusercontent.com/52432049/222877531-17acb753-de2b-4c3c-bb2f-2b90b2d5703a.png)
![회원가입4](https://user-images.githubusercontent.com/52432049/222877532-a74df22b-2146-4a5a-9803-2756206993c5.png)

- 사용자는 유효하지 않은 데이터로는 가입할 수 없으며 이를 피드백으로 볼 수 있다.
- 아이디와 이메일은 실시간으로 중복 체크를 수행하며 이를 피드백으로 볼 수 있다.

![회원가입5](https://user-images.githubusercontent.com/52432049/222877536-5c78daf6-817b-4ca4-ae17-d1e7ad6ab328.png)

- 사용자가 유효한 데이터를 보낼 경우 회원 가입을 할 수 있다.

## 회원 로그인

![회원로그인1](https://user-images.githubusercontent.com/52432049/222877540-c82582ab-78bc-4bb7-b6cd-8670ea4d366e.png)
![회원로그인2](https://user-images.githubusercontent.com/52432049/222877541-396400f5-9b8f-48f3-8e0e-8cedc38a398c.png)

- 사용자는 올바르지 못한 데이터를 보낼 경우 로그인할 수 없다.
- 사용자가 유효한 데이터를 보내고 일치하는 결과가 있다면 로그인에 성공한다.
- 로그인에 성공한 사용자는 토큰을 발급받는다.

## Spring Security 및 JWT

### 1. 로그인

```AuthController.java```
```java
    ...
    @PostMapping("/sign-in")
    public ResponseEntity<TokenDto> authorize(@Valid @RequestBody LoginDto loginDto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getLogin(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + accessToken);

        return new ResponseEntity<>(new TokenDto(accessToken), httpHeaders, HttpStatus.OK);
    }
    ...
```

```CustomUserDetailsService.java```
```java
    ...
    public UserDetails loadUserByUsername(final String login) {
        return usersRepository.findOneWithAuthoritiesByLogin(login)
                .map(users -> createUser(login, users))
                .orElseThrow(() -> new UsernameNotFoundException(login + " -> 데이터베이스에서 찾을 수 없습니다."));
    }
    ...
```

```TokenProvider.java```
```java
    ...
    public String createToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        Date now = new Date();
        Date tokenValidity = new Date(now.getTime() + this.tokenValidityInMilliseconds);

        // Access Token 생성
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(key, SignatureAlgorithm.HS512)
                .setIssuedAt(now)
                .setExpiration(tokenValidity)
                .compact();
    }
    ...
```

- ```/sign-in``` POST요청을 받으면 로그인을 수행한다.
  - 요청으로 들어온 ```LoginDto```의 ```login(username)```과```password```를 파라미터로 받아 ```UsernamePasswordAuthenticationToken```객체를 생성한다.
  - ```UsernamePasswordAuthenticationToken```객체를 파라미터로 받아 ```Authentication```객체를 생성한다.
  - ```authenticate()``` 메소드가 실행될 때, ```CustomUserDetailsService.java```의 ```loadUserByUsername()```메소드가 실행되어 권한 정보를 가져온다.
  - 인증 정보를 담고 있는 ```Authentication``` 객체를 ```SecuritiContext```에 저장한다.
  - ```Authentication``` 객체를 파라미터로 받아 ```createToken()```메소드를 실행시켜 JWT토큰을 생성한 후 사용자에게 리턴한다.

### 실행 결과

![로그인토큰발급](https://user-images.githubusercontent.com/52432049/222886078-5e5af1fa-5245-4e23-91cd-428692d29f59.png)

- 발급된 토큰은 LocalStorage에 저장하고 사용한다.

## 2. Spring Security

```SecurityConfig.java```
```java
...
@Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    ...
    .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()           //홈페이지
                .antMatchers("/post/**").permitAll()    //post api
                .antMatchers("/sign-in").permitAll()    //로그인 api
                .antMatchers("/user/loginCheck/**",
                        "/user/emailCheck/**").permitAll() //유저 아이디, 이메일 중복체크
                .antMatchers("/user").permitAll()       //회원가입 api
                .antMatchers("/user/info").access("hasAnyRole('ADMIN','USER')")
                .antMatchers("/user/info/**").access("hasRole('ADMIN')")
                .anyRequest().authenticated()
    ...
    }
```

- Spring Security 설정
  - ```@EnableWebSecurity```을 통해 웹 보안을 활성화하고, 모든 api요청을 실행하기 전 인증 절차를 요구한다.
  - ```antMatchers.permitAll()``` 설정을 통해 인증 절차가 필요하지 않은 요청을 설정한다.
  - WebSecurityConfigurerAdapter를 상속해 구현하는 방식은 deprecated되었기에 SecurityFilterChain을 반환하고 빈으로 등록함으로써 컴포넌트 기반의 보안 설정을 구현했다.
  > https://jaehoney.tistory.com/249
  - ```/user```, ```/user/info/**``` 요청에 대해서는 권한 기반으로 인증 절차를 요구한다.
  > /user 요청은 로그인한 사용자, /user/info/** 요청은 관리자 권한을 가진 사용자
  
```JWTFilter.java```
```java
    ...
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        // Request Header 에서 JWT 토큰 추출
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String jwt = resolveToken(httpServletRequest);
        String requestURI = httpServletRequest.getRequestURI();

        // TokenProvider.validateToken()으로 토큰 유효성 검사
        if(StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
            Authentication authentication = tokenProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.debug("Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}", authentication.getName(), requestURI);
        } else {
            log.debug("유효한 JWT 토큰이 없습니다, uri: {}", requestURI);
        }

        filterChain.doFilter(servletRequest, servletResponse);
     }
     ...
```

```JwtSecurityConfig.java```
```java
    ...

    @Override
    public void configure(HttpSecurity http) {
        JwtFilter customFilter = new JwtFilter(tokenProvider);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }

    ...
```

```TokenProvider.java```
```java
    ...
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }
    
    ...
    
    public Authentication getAuthentication(String token) {
        Claims claims = parseClaims(token);

        if(claims.get(AUTHORITIES_KEY) == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }
    
    ...
```

- 인증&인가
  - ```SecurityConfig.java```의 인증 절차가 수행되기 전 ```JwtSecurityConfig.java```의 ```configure()``` 메소드에 의해 등록된 커스텀 필터인 ```JWTFilter.java```의 ```doFilter()```메소드가 실행되어 RequestHeader에서 JWT토큰을 추출한다.
  - ```TokenProvider.java```객체의 ```validateToken()```메소드를 통해 토큰의 유효성을 검증하고 유효한 토큰이라면 ```getAutentication()```메소드를 실행해 인증 정보를 가져오고 ```Authentication```객체를 반환한다.
  - ```Authentication```객체의 인증 정보를 ```SecurityContext```에 저장한다.

```UsersRestController.java```
```java
    ...
    
    //유저 정보 조회(개인)
    @GetMapping("/info")
    public ResponseEntity<UsersInfoResponseDto> getMyUsersInfo() {
        UsersInfoResponseDto responseDto = new UsersInfoResponseDto(usersService.getMyUsersWithAuthorities().get());
        return ResponseEntity.ok(responseDto);
    }
    
    ...
    
    //유저 정보 조회(관리자)
    @GetMapping("/info/{login}")
    public ResponseEntity<UsersInfoResponseDto> getUsersInfo(@PathVariable(name = "login") String login) {
        UsersInfoResponseDto responseDto = new UsersInfoResponseDto(usersService.getUsersWithAuthorities(login).get());
        return ResponseEntity.ok(responseDto);
    }
    
    ...
```

```UsersService.java```
```java
    ...
    
    //유저, 권한 정보 조회(관리자)
    @Transactional(readOnly = true)
    public Optional<Users> getUsersWithAuthorities(String login) {
        return usersRepository.findOneWithAuthoritiesByLogin(login);
    }
    
    ...

    //유저, 권한 정보 조회
    @Transactional(readOnly = true)
    public Optional<Users> getMyUsersWithAuthorities() {
        return SecurityUtil.getCurrentUsername().flatMap(usersRepository::findOneWithAuthoritiesByLogin);
    }
    
    ...
```

```SecurityUtil.java```
```java
    ...
    
        public static Optional<String> getCurrentUsername() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null) {
            log.debug("Security Context에 인증 정보가 없습니다.");
            return Optional.empty();
        }

        String username = null;
        if(authentication.getPrincipal() instanceof UserDetails) {
            UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
            username = springSecurityUser.getUsername();
        } else if(authentication.getPrincipal() instanceof String) {
            username = (String) authentication.getPrincipal();
        }

        return Optional.ofNullable(username);
    }
    
    ...
```

- 요청 실행 및 인증 절차
  - 권한을 기반으로 ```SecurityConfig.java```의 인증 절차를 통과하면 요청을 실행한다. 이 때, 인증 정보는 SecurityContext에 저장되어 있다.
  - ```/user/info/**``` 요청은 관리자가 특정 사용자의 정보를 리턴 받을 수 있으며, 요청받은 ```login(username)```을 파라미터로 사용자의 정보를 반환 받는다.
  - ```/user/info``` 요청은 로그인한 사용자가 자신의 정보를 리턴 받을 수 있으며, ```SecurityContext```에 저장된 자신의 인증 정보를 ```SecurityUtil.java```에 구현한 ```getCurrentUsername()```메소드를 실행하여 가져와 사용자 정보를 얻을 수 있다.
  > getCurrentUsername()메소드에서 SecurityContextHolder.getContext().getAuthentication() 를 실행하여 인증 정보를 가져온다.
  
```JwtAccessDeniedHandler.java```
```java
    ...
    
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        response.sendError(HttpServletResponse.SC_FORBIDDEN);
    }
    
    ...
```

```JwtAuthenticationEntryPoint.java```
```java
    ...

    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }

    ...
```

```SecurityConfig.java```
```java
    ...

    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        ...
        
        .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)
        
        ...
    }
    
    ...
```

- ExceptionHandling
  - Jwt 인증 절차중에 발생할 수 있는 Exception을 Handling한다.
  - ```JwtAccessDeniedHandler.java```는 인가되지 않은 사용자가 접근하려 할 때 403에러가 발생하는데 이를 Handling한다.
  - ```JwtAuthenticationEntryPoint.java```는 인증되지 않은 사용자가 접근하려 할 때 사용자가 401에러가 발생하는데 이를 Handling한다.
  - ```SeurityConfig.java```의 설정에  ```JwtAccessDeniedHandler.java```와 ```JwtAuthenticationEntryPoint.java```를 등록하여 해당 객체로 Exception을 Handling한다.

![인증인가](https://user-images.githubusercontent.com/52432049/222895393-2bc9d557-f40a-4c47-afc6-e9f9164a8724.png)
![인증인가2](https://user-images.githubusercontent.com/52432049/222895394-df04e2c2-b1f1-409b-8172-32eb0c0c1b90.png)

- 관리자 권한을 가진 사용자는 ```/user/info```, ```/user/info/**```요청에 대해 모두 응답 받을 수 있다.

![인증인가3](https://user-images.githubusercontent.com/52432049/222895396-988f022e-498f-48b6-8286-756b28013e51.png)

- 관리자 권한을 가지지 못한 사용자는 ```/user/info/**``` 요청에 대해 ```JwtAccessDeniedHandler.java```에 의해 403에러를 리턴 받는다.

# 3. 프로젝트 후기
