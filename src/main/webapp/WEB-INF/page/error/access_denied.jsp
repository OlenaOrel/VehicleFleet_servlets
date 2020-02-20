<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <%@ include file="/WEB-INF/page/fragments/header.jsp" %>
</head>
<body>
<div class="container text-center">
    <h3>403</h3>
    <h1><fmt:message key="message.error.403.title"/></h1>
    <h5><fmt:message key="message.error.403.description"/></h5>
    <a href="${pageContext.request.contextPath}/"><fmt:message key="message.main.page"/></a>
</div>
</body>
</html>