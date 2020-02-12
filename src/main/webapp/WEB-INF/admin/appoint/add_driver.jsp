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
</head>
<body>
<div class="container" style="margin-top: 50px">
    <div class="row">
        <div class="col-md-10 col-md-offset-2">
            <div id="add driver">
                <c:if test="${driverList != null}">
                    <table class="table">
                        <tr>
                            <th>
                                <fmt:message key="message.user.name"/>
                            </th>
                            <th>
                                <fmt:message key="message.user.surname"/>
                            </th>
                            <th>
                                <fmt:message key="message.driver.add"/>
                            </th>
                        </tr>
                        <c:forEach var="driver" items="${driverList}">
                            <tr>
                                <c:if test="${lang == 'en'}">
                                    <td>${driver.firstName}</td>
                                    <td>${driver.lastName}</td>
                                </c:if>
                                <c:if test="${lang == 'uk'}">
                                    <td>${driver.orignFirstName}</td>
                                    <td>${driver.orignLastName}</td>
                                </c:if>
                                <td>
                                    <form action="${pageContext.request.contextPath}/admin/appoint/driver"
                                          method="post">
                                        <input type="hidden" name="driverId" value="${driver.id}">
                                        <input type="submit" value='<fmt:message key="message.driver.add"/>'
                                               class="btn btn-default" style="margin-top:10px">
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