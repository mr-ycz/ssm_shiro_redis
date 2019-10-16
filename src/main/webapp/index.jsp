<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    欢迎你,
    <shiro:user>
        <shiro:principal/>

        <a href="${pageContext.request.contextPath}/shiro/logout"><span style="float: right">注销</span></a>

        <hr>

        <shiro:hasPermission name="fight">
            <a href="${pageContext.request.contextPath}/shiro/fight">鞭打秦源狗</a>
        </shiro:hasPermission>
    </shiro:user>
</form>
</body>
</html>
