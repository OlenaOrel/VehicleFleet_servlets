<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <meta charset="UTF-8">
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
            <div class="row">
                <div class="col-md-2 col-md-offset-10">
                    <div class="locale">
                        <a href="?lang=en">EN</a>|<a href="?lang=uk">UA</a>
                    </div>
                    <form action="${pageContext.request.contextPath}/logout" method="GET">
                        <input type="submit" value='<fmt:message key="message.logout"/>'
                               class="btn btn-default" style="margin-top:10px">
                    </form>
                </div>
            </div>
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
            <c:if test="${confirm == true}">
                <h3><fmt:message key="message.appoint.confirmed"/></h3>
            </c:if>
            <c:if test="${appointPresent == true}">
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
                        <td>
                            <form action="${pageContext.request.contextPath}/driver"
                                  method="post">
                                <input type="hidden" name="confirm" value="${true}">
                                <input type="submit" value='<fmt:message key="message.route.confirm"/>'
                                       class="btn btn-default">
                            </form>
                        </td>
                    </tr>
                </table>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>