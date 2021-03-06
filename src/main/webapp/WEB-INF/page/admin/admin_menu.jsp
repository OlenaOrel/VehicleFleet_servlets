<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${sessionScope.get('lang')}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <%@ include file="/WEB-INF/page/fragments/header.jsp" %>

    <div class="container">
        <div class="row">
            <div class="col-md-12 col-md-offset-2">
                <div align="right">
                    <h5>${sessionScope.userDto.email}</h5>
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
        <div class="col-md-12 col-md-offset-2">
            <form action="${pageContext.request.contextPath}/admin/appoint/route">
                <input type="submit" value='<fmt:message key="message.route.appoint"/>'
                       class="btn btn-default" style="margin-top:10px">
            </form>
            <form action="${pageContext.request.contextPath}/admin/history">
                <input type="submit" value='<fmt:message key="message.appoint.history"/>'
                       class="btn btn-default" style="margin-top:10px">
            </form>
            <br>
            <div id="appointment history">
                <c:if test="${! appointmentDtoList.isEmpty()}">
                    <h4 align="center"><fmt:message key="message.appointment.active"/></h4>
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
                                <fmt:message key="message.bus.license.plate"/>
                            </th>
                            <th>
                                <fmt:message key="message.bus.mark"/>
                            </th>
                            <th>
                                <fmt:message key="message.driver"/>
                            </th>
                            <th>
                                <fmt:message key="message.appoint.finish"/>
                            </th>
                        </tr>
                        <c:forEach items="${appointmentDtoList}" var="appointmentDto">
                            <tr>
                                <td>${appointmentDto.routeNumber}</td>

                                <c:if test="${lang.equals('en')}">
                                    <td>${appointmentDto.routeDeparture}</td>
                                    <td>${appointmentDto.routeArrival}</td>
                                </c:if>

                                <c:if test="${lang.equals('uk')}">
                                    <td>${appointmentDto.routeDepartureUk}</td>
                                    <td>${appointmentDto.routeArrivalUk}</td>
                                </c:if>

                                <td>${appointmentDto.busLicensePlate}</td>
                                <td>${appointmentDto.busMark}</td>

                                <c:if test="${lang.equals('en')}">
                                    <td>${appointmentDto.driverFullName}</td>
                                </c:if>

                                <c:if test="${lang.equals('uk')}">
                                    <td>${appointmentDto.driverFullNameUk}</td>
                                </c:if>
                                <td>
                                    <form action="${pageContext.request.contextPath}/admin"
                                          method="post">
                                        <input type="hidden" name="appointmentId" value="${appointmentDto.id}">
                                        <input type="submit" value='<fmt:message key="message.appoint.finish"/>'
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
</div>
</body>
</html>
