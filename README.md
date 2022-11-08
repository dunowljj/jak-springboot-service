Spring Boot, Spring Data JPA 학습용 프로젝트
====

## 목적
1. 인프런 영한님 JPA강의 적용하며 학습
   - 1차 적용 : 기본편, 실전 1을 활용하여 간단한 로직 구현
   - 1차 적용 완료 후 실전 2 적용
   - 추후에 Spring Data JPA, Querydsl, DB접근 기술 등 학습에 활용 
2. 스프링 부트와 AWS로 혼자 구현하는 웹 서비스 책 학습
   - 시작부터 배포까지 전 과정 이해
   - 협력 프로젝트에 참여하기 위한 기반
   - 테스트, 배포 자동화, 시큐리티 이해
3. 국비 프로젝트 리팩터링
   - 기존 프로젝트의 기능 대부분 삭제, 단순화하여 학습용으로 진행. 사실상 리팩터링이 아닌 부분 새로 구현
   - 학습 내용이 부족하다 느끼거나 혹은 필요에 따라 기능 추가

## 요구사항 및 설계
### 이전 프로젝트 추출본
<img width="700" alt="before_erd" src="https://user-images.githubusercontent.com/92416661/200156225-a3129ded-501b-416d-9392-690a5ebe7d3b.png">

### 1차 요구사항
- 회원은 소셜 로그인이 가능하다. 
- 회원은 "진행중인" 행사의 티켓을 구매할 수 있다.
- 회원은 예약한 티켓의 결제를 진행할 수 있다.
- 보라책의 흐름을 따라갈겸, 게시판을 만든다. (후에 리뷰게시판으로 활용) 
+ dtype 연습을 위해 행사장 유형 분류
+ 임의로 행사는 그냥 등록 가능 (후에 회원 등급 나누기)
+ 모두 약식, 간단한 버튼만 구현

<img width="715" alt="model" src="https://user-images.githubusercontent.com/92416661/200159010-aa984ce8-e135-4ecb-acf7-ea12bfe9a84f.png">

**UI** 
- 로그인/로그아웃, 행사등록/조회, 티켓예약/예약목록, 티켓결제/결제목록
