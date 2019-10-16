<%--
  Created by IntelliJ IDEA.
  User: zhj
  Date: 2019/10/15
  Time: 11:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/shiro/regist" method="post">
    username: <input type="text" name="username"> <br>
    password: <input name="password" type="password"> <br>
    <input type="submit" value="注册">
</form>
</body>
</html>
