<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${sessionScope.get('lang')}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <%@ include file="/WEB-INF/page/fragments/header.jsp" %>
</head>
<body>
<div class="container" style="margin-top: 60px">
    <div class="col-md-8 col-md-offset-4">
        <h2>
            <fmt:message key="message.app.welcome"/>
        </h2>
        <div class="alert alert-error" style="margin-top:20px" bgcolor="red">
            <c:if test="${error == true}">
                <fmt:message key="message.invalid.email.password"/>
            </c:if>
            <c:if test="${logout == true}">
                <fmt:message key="message.invalid.email.password"/>
            </c:if>
        </div>
        <br/>
        <form action="${pageContext.request.contextPath}/login">
            <input type="submit" value='<fmt:message key="message.login"/>'
                   class="btn btn-default">
        </form>
        <br>
        <form action="${pageContext.request.contextPath}/register">
            <input type="submit" value='<fmt:message key="message.sign.up"/>'
                   class="btn btn-default">
        </form>
        <br>
    </div>
</div>
</body>
</html>
