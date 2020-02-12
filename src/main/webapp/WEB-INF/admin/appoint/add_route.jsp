<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="messages"/>

<html>
<head>
    <meta charset="UTF-8">
    <title>
        <fmt:message key="message.app.name"/>
    </title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<div class="col-md-8 col-md-offset-2">
    <h1 style="color: cornflowerblue">
        <fmt:message key="message.app.name"/>
    </h1>
    <div class="col-md-12 col-md-offset-12" align="right">
        <div class="locale">
            <a href="?lang=en">EN</a>|<a href="?lang=uk">UA</a>
        </div>
        <form action="${pageContext.request.contextPath}/logout" method="GET">
            <input type="submit" value='<fmt:message key="message.logout"/>'
                   class="btn btn-default" style="margin-top:10px">
        </form>

    </div>
</div>
<div class="container" style="margin-top: 60px">
    <div class="row">
        <div class="col-md-10 col-md-offset-2">
            <c:if test="${rootes.isEmpty()}">
                <h3>There is no route found</h3>
            </c:if>
            <c:if test="${!rootes.isEmpty()}">
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
                            <fmt:message key="message.route.appoint"/>
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
                                    <input type="submit" value='<fmt:message key="message.route.appoint"/>'
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