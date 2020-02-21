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
    <h1><fmt:message key="message.error.500.title"/></h1>
    <h5><fmt:message key="message.error.500.description"/></h5>
    <h5><fmt:message key="message.error.500.description1"/></h5>
    <a href="${pageContext.request.contextPath}/"><fmt:message key="message.main.page"/></a>
</div>
</body>
</html>