<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${sessionScope.get('lang')}"/>
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
        <div class="col-md-12 col-md-offset-2">
            <form action="${pageContext.request.contextPath}/admin">
                <input type="submit" value='<fmt:message key="message.main.page"/>'
                       class="btn btn-default" style="margin-top:10px">
            </form>
            <br>
            <div id="appointment history">
                <c:if test="${page.elements.size() > 0}">
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
                                <fmt:message key="message.appoint.date"/>
                            </th>
                            <th>
                                <fmt:message key="message.appoint.status"/>
                            </th>
                        </tr>
                        <c:forEach items="${page.elements}" var="appointmentDto">
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
                                    <tags:localDate date="${appointmentDto.date}" pattern="dd.MM.yyyy"/>
                                </td>
                                <td>
                                    <c:if test="${appointmentDto.status.name() == 'NEW'}">
                                        <fmt:message key="message.appointment.status.new"/>
                                    </c:if>
                                    <c:if test="${appointmentDto.status.name() == 'CONFIRMED'}">
                                        <fmt:message key="message.appointment.status.confirmed"/>
                                    </c:if>
                                    <c:if test="${appointmentDto.status.name() == 'FINISHED'}">
                                        <fmt:message key="message.appointment.status.finished"/>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
                <nav>
                    <c:if test="${page.totalPage > 1}">
                        <ul class="pagination">
                            <c:forEach begin="1" end='${page.totalPage}' var="i">
                                <c:choose>
                                    <c:when test="${page.currentPage + 1 eq i}">
                                        <li class="page-item active"><a class="page-link">
                                                ${i} </span></a>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="page-item"><a class="page-link"
                                                                 href="?page=${i}">${i}</a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </ul>
                    </c:if>
                </nav>
            </div>
        </div>
    </div>
</div>
</body>
</html>
