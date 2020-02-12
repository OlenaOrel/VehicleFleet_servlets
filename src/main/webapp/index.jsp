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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <div class="container" style="margin-top: 60px">
        <div class="col-md-10 col-md-offset-4">
            <h1 style="color: cornflowerblue">
                <fmt:message key="message.app.name"/>
            </h1>
            <div class="col-md-10 col-md-offset-9" align="right">
                <div class="locale">
                    <a href="?lang=en">EN</a>|<a href="?lang=uk">UA</a>
                </div>
            </div>
        </div>
    </div>
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
