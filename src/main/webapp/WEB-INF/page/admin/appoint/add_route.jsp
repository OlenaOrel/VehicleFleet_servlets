<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <%@ include file="/WEB-INF/page/fragments/header.jsp" %>

    <div class="container" style="margin-top: 60px">
        <div class="row">
            <div class="col-md-12 col-md-offset-2">
                <div align="right">
                    <form action="${pageContext.request.contextPath}/logout" method="Post">
                        <input type="submit" value='<fmt:message key="message.logout"/>'
                               class="btn btn-default" style="margin-top:10px">
                    </form>
                </div>
            </div>
        </div>
    </div>
</head>
<body>
<div class="container" style="margin-top: 60px">
    <div class="row">
        <div class="col-md-10 col-md-offset-2">
            <c:if test="${isRouteListEmpty == true}">
                <h3><fmt:message key="message.empty.route.list"/></h3>
            </c:if>
            <c:if test="${isRouteListEmpty == false}">
                <table class="table">
                    <tr>
                        <th>
                            <fmt:message key="message.route.number"/>
                        </th>
                        <th>
                            <fmt:message key="message.route.departure"/>
                        </th>
                        <th>
                            <fmt:message key="message.route.arrival"/>
                        </th>
                        <th>
                            <fmt:message key="message.route.add"/>
                        </th>
                    </tr>
                    <c:forEach items="${routeList}" var="route">
                        <tr>
                            <td>${route.number}</td>
                            <c:if test="${lang.equals('en')}">
                                <td>${route.departureFromCityEn}</td>
                                <td>${route.arrivalToCityEn}</td>
                            </c:if>
                            <c:if test="${lang.equals('uk')}">
                                <td>${route.departureFromCityUk}</td>
                                <td>${route.arrivalToCityUk}</td>
                            </c:if>
                            <td>
                                <form action="${pageContext.request.contextPath}/admin/appoint/route"
                                      method="post">
                                    <input type="hidden" name="routeId" value="${route.id}">
                                    <input type="submit" value='<fmt:message key="message.route.add"/>'
                                           class="btn btn-default">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>