<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <%@ include file="/WEB-INF/page/fragments/header.jsp" %>
</head>
<div class="container text-center">
    <h1><fmt:message key="message.error.title"/></h1>
    <h5><fmt:message key="message.error.description"/></h5>
    <a href="${pageContext.request.contextPath}/"><fmt:message key="message.main.page"/></a>
</div>
</html>