# 🐥 Sejong Coding Helper
> 세종대학교 코딩 수업을 수강하는 학생들의 효율적인 코딩 학습을 위한 코딩 도우미 플랫폼입니다.<br>
> 딥러닝 기반 Q&A 챗봇 기능 및 TA 조교 채팅 기능 등을 제공합니다.
<br>
<p align="center"> <img src = "https://user-images.githubusercontent.com/64072741/153468465-b943be43-f00b-44fe-9680-6fe32a2831b7.png"> </p><br>

##  💡  Background
1. **학생들이 실시간으로 답변을 받기 힘든 현행 코딩 수업 구조** : TA 조교가 많은 학생들에 대한 모든 질문을 답변하기에는 꽤 큰 부담이며, 중복된 질문 또한 매우 많습니다. 
결정적으로 질문과 답변이 이루어지는 시간이 다소 오래 걸립니다.
2. **학습 자료를 찾는 어려움** : 코딩에 처음 접해 능숙하지 못한 학생의 경우 검색을 통해서 양질의 학습 자료를 찾는 것이 어려우며, 이에 시간이 오래 걸립니다.
3. **TA 조교 인력의 부족** : 코딩 수업을 수강하는 학생 수에 비해 TA조교 인력이 부족함에 따라 다양한 문제점이 발생하고 있습니다.
4. **원활한 수업 관리의 어려움** : 현재 TA조교와 담당 학생과의 질문이 개인 카카오톡이나 오픈 채팅을 통해 이루어집니다. 
이 때, TA조교의 사적인 영역을 침해할 우려가 크며 오픈 채팅과 같은 경우는 신원 확인이 어렵습니다.<br><br>

##  📝  Features
> 각 항목의 Features Preview를 클릭하면 기능 스크린샷을 볼 수 있습니다.
<br>

### 1. 코딩 Q&A 챗봇 기능
```
✔️ 코딩 수업을 수강하는 학생들이 시간 제약을 받지 않고 실시간으로 챗봇에게 질문 가능.
✔️ 딥러닝 모델을 통해 사용자 질문에서 의도, 개체명 인식 → 인식한 의도, 개체명 키워드를 이용하여 답변 검색.
✔️ 사용자 질문 키워드 추출 → TF-IDF, 코사인 유사도 분석을 통한 키워드 유사도 높은 질문 추천.
```
<details>
<summary><b>💻 Features Preview</b></summary>
<p align="center"> <img src = "https://user-images.githubusercontent.com/64072741/153472679-6bc866a8-2a4a-42fa-8a53-af9d5c0f96ed.jpg"> </p><br>
</details>

<br>

### 2. TA조교와 채팅 기능
```
✔️ Q&A 챗봇을 통해서 해결되지 않은 질문을 TA조교와 1대 1채팅을 통해 해결 가능.
✔️ 코드와 컴파일 결과를 편리하게 조교에게 전송 가능.
```
<details>
<summary><b>💻 Features Preview</b></summary>
<p align="center"> <img src = "https://user-images.githubusercontent.com/64072741/153473041-3562fba4-f2cc-41da-8ca0-483fe15b48d8.jpg"> </p><br>
<p align="center"> <img src = "https://user-images.githubusercontent.com/64072741/153473215-61ff974e-99e6-4952-99ad-a95d10827fb0.jpg"> </p><br>
</details>

<br>

### 3. 웹 코드 컴파일러 기능
```
✔️ 편리한 웹 컴파일 환경 제공.
✔️ zt-exec Library를 사용해 서버 gcc Compiler, python3 에 접근한 다음 결과 값 추출.
✔️ 조교에게 코드 및 입, 출력 결과 간편 전송 가능.
```

<details>
<summary><b>💻 Features Preview</b></summary>
<p align="center"> <img src = "https://user-images.githubusercontent.com/64072741/153474450-db73e8d2-575b-4a6f-857b-f9b92a64be1d.jpg"> </p><br>
<p align="center"> <img src = "https://user-images.githubusercontent.com/64072741/153474949-03e3522e-94a9-4968-8274-08f550365576.jpg"> </p><br>
</details>

<br>


##  📚  Stack & Library

### Front-End
+ ReactJS
+ Redux

### Back-End
+ Spring Boot
+ Spring JPA
+ Flask
+ MySQL

### Machine Learning
+ TensorFlow
+ Scikit Learn

### Intrastructure
+ AWS EC2, RDS
+ Nginx
+ Docker

<br>

##  🛠️  Architecture
<p align="center"> <img src = "https://user-images.githubusercontent.com/64072741/153477922-7ffb7423-249a-484c-8887-aa18b7b7b822.png"> </p><br>

## 💻 My Part
+ 플랫폼의 UI/UX 디자인 및 설계를 담당했습니다.
+ React를 활용하여 전반적인 Frontend 기능 개발을 담당했습니다.
+ 실시간 채팅 기능 개발을 위해 STOMP 웹 소켓 통신의 클라이언트 사이드 파트를 개발했습니다.
+ Spring Security 기반 JWT를 활용하여 회원가입 및 로그인 보안 처리를 담당했습니다.
+ 클라이언트 사이드에 필요한 DB 데이터를 전송하는 api를 일부 개발했습니다.
+ 머신러닝 돌아가는 Flask 기반 서버를 구축하였고, 서버 to 서버 통신을 처리했습니다.
<br>

## 🔍 More
<p align="center"> <a href="https://www.youtube.com/watch?v=2Y8-H26Ypds"><img src="https://user-images.githubusercontent.com/64072741/153478838-f0929818-e243-4ea1-9ed1-7a3c9fbc293e.png"/></a> </p>


