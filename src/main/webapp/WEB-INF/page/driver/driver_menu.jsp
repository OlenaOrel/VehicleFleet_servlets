<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <%@ include file="/WEB-INF/page/fragments/header.jsp" %>
    <div class="container" style="margin-top: 60px">
        <div class="col-md-12 col-md-offset-2" align="right">
            <h5>${sessionScope.userDto.email}</h5>
            <form action="${pageContext.request.contextPath}/logout" method="GET">
                <input type="submit" value='<fmt:message key="message.logout"/>'
                       class="btn btn-default" style="margin-top:10px">
            </form>
        </div>
    </div>
</head>
<body>
<div class="container" style="margin-top: 50px">
    <div class="row">
        <div class="col-md-12 col-md-offset-2">
            <c:if test="${appointPresent == false}">
                <h3><fmt:message key="message.appoint.not.present"/></h3>
            </c:if>
            <c:if test="${appointPresent == true}">
                <h3><fmt:message key="message.driver.appointed"/></h3>
                <table class="table" style="margin-top: 20px">
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
                            <fmt:message key="message.bus.license.plate"/>
                        </th>
                        <th>
                            <fmt:message key="message.bus.mark"/>
                        </th>
                    </tr>
                    <tr>
                        <td>${sessionScope.appointDto.routeNumber}</td>
                        <c:if test="${lang.equals('en')}">
                            <td>${sessionScope.appointDto.routeDeparture}</td>
                            <td>${sessionScope.appointDto.routeArrival}</td>
                        </c:if>
                        <c:if test="${lang.equals('uk')}">
                            <td>${sessionScope.appointDto.routeDepartureUk}</td>
                            <td>${sessionScope.appointDto.routeArrivalUk}</td>
                        </c:if>
                        <td>${sessionScope.appointDto.busLicensePlate}</td>
                        <td>${sessionScope.appointDto.busMark}</td>
                    </tr>
                </table>
                <c:if test="${sessionScope.appointDto.status.name() == 'NEW'}">
                    <form action="${pageContext.request.contextPath}/driver"
                          method="post">
                        <input type="hidden" name="appointmentId" value="${sessionScope.appointDto.id}">
                        <input type="submit" value='<fmt:message key="message.route.confirm"/>'
                               class="btn btn-default">
                    </form>
                </c:if>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>