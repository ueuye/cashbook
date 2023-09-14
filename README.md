# cashbook

# 🖥 개요  
- 2023-06-30 ~ 2023-08-01
- Model 2 방식을 이용한 가계부
- 개인프로젝트

# 🛠️ 개발 환경
- Language (언어) : `HTML5` `CSS3` `Java`, `SQL`, `JavaScript`, `Ajax`
- Library (라이브러리): `Bootstrap`, `jQuery`, `JSTL`
- Database (데이터베이스) : `MariaDB`
- WAS (Web Application Server) : `Apache Tomcat9`
- OS(운영체제): `MAC`
- TOOL : `Eclipse IDE`, `sequelAce`

# 📌 주요 기능

- 로그인 / 로그아웃
  - 카카오 로그인 API를 이용하여 **소셜 로그인**
  - **Cookie**에 저장된 아이디가 있다면 세션에 저장하여 하루동안 아이디 저장
  - 로그인 / 로그아웃 시 **Session** 생성 및 삭제
- 회원가입
  - ajax를 활용한 아이디 중복 검사
- 가계부 캘린더
  - Java 기본 API의 Calendar Class 사용하여 달력을 구현하여 가계부를 구성
  - 일별 총 수입/지출 출력
  - 가계부 추가, 수정 시 상위 카테고리를 선택하면 ajax를 활용하여 하위 카테고리 선택
- 해시태그
  - 가계부 작성 시 #을 사용하여 해시태그 작성
  - **replace** 및 **split**을 사용하여 공백을 기준으로 #을 삭제 후 DB저장
- Filter
  - 모든페이지가 utf-8로 인코딩
  - 로그인 유효성검사
- Listener
  - **방문자 수 카운트**(현재접속자, 오늘접속자, 누적접속자) 출력
  - MariaDB 연결
- 그래프
  - Ajax와 **chart.js**를 이용하여 그래프 구현
  - 월별 총 수입/지출 그래프
  - 최근 3개월 소비 현황 그래프
  - 월별/카테고리별 소비 현황 그래프 

# 🔎 주요 구현화면 미리보기
|로그인화면|메인화면|
|--|--|
|<img width="800" alt="cashbook_1" src="https://github.com/ueuye/cashbook/assets/133735744/df11fcca-aff5-4218-91ae-b37f6b94c794">|<img width="800" alt="cashbook_2" src="https://github.com/ueuye/cashbook/assets/133735744/798cb2aa-9a0e-4a19-bad9-94e8e7c39687">|

|해시태그|차트|
|--|--|
|<img width="800" alt="cashbook_3" src="https://github.com/ueuye/cashbook/assets/133735744/2c7b6702-14a5-4e11-983d-4a8c6898f893">|<img width="800" alt="cashbook_4" src="https://github.com/ueuye/cashbook/assets/133735744/ebc2ca44-0ddc-40a5-89c1-8b5778060979">|
