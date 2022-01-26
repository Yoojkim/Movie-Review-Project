<%--
  Created by IntelliJ IDEA.
  User: yjee0
  Date: 2022-01-16
  Time: 오후 8:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div class="container">
        <form action="/members/new" method="post">
            <div class="form-group">
                <label for="name">이름</label>
                <input type="text" id="name" name="name" placeholder="이름을 입력하세요">
            </div>
            <button type="submit">등록</button>
        </form>
    </div> <!-- /container -->
</body>
</html>
