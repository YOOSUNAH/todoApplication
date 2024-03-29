## SpringBoot-Project-TodoApplication
### 스프링 부트 + JWT 

스파르타-내배캠-개인과제

노션 링크 : <https://robust-tiglon-c3e.notion.site/8b69540ade674eb9990a0357036c99d1>


🖥️ 프로젝트 소개
---
* 회원가입 하고 로그인 후, Todo를 작성하고 관리하는 App
* 이 프로젝트는 Spring Framework 기반의 프로젝트로 TODO LIST 기능을 구현하고있는 프로젝트 입니다.

⚙️ 개발 환경
---
* Java 8
* JDK 17
* Framework : Springboot(3.x)
* Database : MySql

⚙️ API 명세서
* <https://documenter.getpostman.com/view/28175588/2s9YyzcHmt>
 <img width="769" alt="스크린샷 2024-02-09 오전 11 28 14" src="https://github.com/YOOSUNAH/todoApplication/assets/120374640/a58714c1-4edc-4d4e-8d56-1e12b0ff60b1">
 <img width="760" alt="스크린샷 2024-02-09 오전 11 29 05" src="https://github.com/YOOSUNAH/todoApplication/assets/120374640/19f31083-e75e-4bda-afbc-3e1d4f575cbc">

⚙️ ERD
---
* <https://www.erdcloud.com/d/4KRjnXDqvAyHi99ym>
  <img width="709" alt="스크린샷 2024-02-09 오전 11 26 13" src="https://github.com/YOOSUNAH/todoApplication/assets/120374640/00c4fb26-5076-4e7c-b913-950ab96863e0">


📌 주요 기능
---
* 회원 가입
* 로그인 
  * DB값 검증
  * ID찾기, PW찾기
  * 로그인 시 토큰(Token) 및 세션(Session) 생성

* Todo(할일) 생성
  * 토큰 확인 후 생성
* Todo(할일) 전체 조회
  * 토큰 확인 후 조회
* Todo(할일) 선택 하여 수정
  * 토큰 확인 후 수정
* Todo(할일) 선택 하여 삭제
  * 토근 확인 후 삭제
 
* 할일카드 완료 기능 API
  * update시, 작성자가 만든 Todo의 완료 여부가 true로 변환된다.
  * Todo(할일) 목록 조회시, 해당 작성자가 update 하여, 완료(true)된 Todo(할일)을 Todo목록을 통해 확인 할 수 있다.
    
* 예외 처리 (ResponseEntity 사용) 일부 구현
  - [X] DB에 이미 존재하는 username으로 회원가입을 요청한 경우 "중복된 username 입니다." 라는 에러메시지와 statusCode: 400을 Client에 반환
