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

# 3. 프로젝트 후기
