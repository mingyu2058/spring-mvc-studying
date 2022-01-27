<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<!-- 상대경로 사용, [현재 URL이 속한 계층 경로 + /save] -->
<!-- 즉 이렇게 되면 127.0.0.1:8080/servlet-mvc/members/save 이런식으로 됨 -->
<!-- 만약 절대 경로로 하고 싶으면 action="/save" 사용 -->
<!-- WEB-INF는 외부에서 url 넣어서 바로 접근 불가 -->

<form action="save" method="post">
    username: <input type="text" name="username"/>
    age : <input type="text" name="age"/>
    <button type="submit">전송</button>
</form>

</body>
</html>
