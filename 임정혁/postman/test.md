### URL 얻기
```
GET http://localhost:8080/api/oauth2/login/google

{"accessToken":"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiUm9sZSI6IlVTRVIiLCJleHAiOjE3NjMzMDY0NTd9.KV3x8g4X1PYUDR2qMmWIMH2S-qsdxwsxutTGMmyGo-k"}
```
<img width="1425" height="604" alt="image" src="https://github.com/user-attachments/assets/2ea7be45-a111-4a25-850b-cfbb6db2f657" />

<img width="1105" height="49" alt="image" src="https://github.com/user-attachments/assets/da760963-ceb3-48f5-b03a-3ef2b0416f5e" />

### 사용자 정보 조회

```
GET http://localhost:8080/gdg/test

{
    "id": 1,
    "email": "imjeonghyeog515@gmail.com",
    "userName": "임정혁",
    "password": null,
    "profileUrl": "https://lh3.googleusercontent.com/a/ACg8ocLh-cBGwlYANc2oPQgUko5fy03HgO1IS6rzqCja93C9cG5Xsgtu=s96-c",
    "role": "USER",
    "createdAt": "2025-11-16T23:20:57.015939"
}

```

<img width="1470" height="677" alt="image" src="https://github.com/user-attachments/assets/9584cf92-5007-4e0c-b4e5-6fc254f9fd1c" />

### Todo 생성

```
POST http://localhost:8080/api/todos

{
    "title": "첫 번째 Todo",
    "description": "Postman으로 생성한 첫 번째 할일입니다",
    "priority": "HIGH",
    "dueDate": "2025-12-31",
    "completed": false
}
```

<img width="1468" height="752" alt="image" src="https://github.com/user-attachments/assets/b22933bd-0463-4dae-9c65-d45fcb93cab0" />

### Todo 조회

```
GET http://localhost:8080/api/todos
```

<img width="1467" height="786" alt="image" src="https://github.com/user-attachments/assets/4afffec4-f936-4938-918c-2ed67d66eaf4" />

### Todo 단건 조회

```
GET http://localhost:8080/api/todos
```
<img width="2106" height="971" alt="image" src="https://github.com/user-attachments/assets/501d0683-983f-49ff-ba45-721fc162fe3e" />

### Todo 수정

```
PUT http://localhost:8080/api/todos/1

{
    "title": "수정된 Todo",
    "description": "수정 테스트",
    "priority": "MEDIUM",
    "dueDate": "2025-12-15",
    "completed": true
}
```

<img width="2128" height="1012" alt="image" src="https://github.com/user-attachments/assets/0efaa7b2-8bd1-4dbd-b51a-23c5db397e28" />


### Todo 삭제


```
DELETE http://localhost:8080/api/todos/1
```

![Uploading image.png…]()

