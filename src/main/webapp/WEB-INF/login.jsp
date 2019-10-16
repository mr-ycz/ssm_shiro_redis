<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/15
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录界面</title>
</head>
<body>
    <form href="${pageContext.request.contextPath}/user/login" method="post">
        username:<input type="text" name="username" /> <br>
        password:<input type="text" name="password" /> <br>
        <input type="submit" value="登录" />
    </form>
</body>
</html>
