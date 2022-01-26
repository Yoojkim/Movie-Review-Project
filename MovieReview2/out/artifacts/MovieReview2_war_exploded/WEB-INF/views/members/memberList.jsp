<%--
  Created by IntelliJ IDEA.
  User: yjee0
  Date: 2022-01-16
  Time: 오후 8:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div class="container">
        <div>
            <table>
                <thead>
                <tr>
                    <th>#</th>
                    <th>이름</th>
                </tr>
                </thead>
                <tbody>
                <tr th:each="member : ${members}"> <!--th:each = 루프 돌면서 로직 실행-->
                    <td th:text="${member.id}"></td>
                    <td th:text="${member.name}"></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div> <!-- /container -->
    </body>
</html>
