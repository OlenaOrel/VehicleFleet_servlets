<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${sessionScope.get('lang')}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title>
        <fmt:message key="message.app.name"/>
    </title>
    <div class="col-md-12 col-md-offset-10" align="right">
        <div class="locale">
            <a href="?lang=en">EN</a>|<a href="?lang=uk">UA</a>
        </div>
    </div>
</head>
<body>
<h2>
    <fmt:message key="message.app.welcome"/>
</h2>
<div class="alert alert-error" style="margin-top:20px" bgcolor="red">
    <c:if test="${error == true}">
        <fmt:message key="message.invalid.email.password"/>
    </c:if>
    <c:if test="${logout}">
        <fmt:message key="message.invalid.email.password"/>
    </c:if>
</div>
<br/>
<a href="${pageContext.request.contextPath}/login">
    <fmt:message key="message.login"/>
</a>
<br>
<a href="${pageContext.request.contextPath}/register">
    <fmt:message key="message.sign.up"/>
</a>
<br>
</body>
</html>
