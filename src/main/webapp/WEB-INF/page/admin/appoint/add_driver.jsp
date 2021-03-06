<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${sessionScope.lang}"/>
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
<div class="container" style="margin-top: 20px">
    <div class="row">
        <div class="col-md-10 col-md-offset-2">
            <form action="${pageContext.request.contextPath}/admin">
                <input type="submit" value='<fmt:message key="message.main.page"/>'
                       class="btn btn-default" style="margin-top:10px">
            </form>
            <form action="${pageContext.request.contextPath}/admin/appoint/bus">
                <input type="submit" value='<fmt:message key="message.back"/>'
                       class="btn btn-default" style="margin-top:10px">
            </form>
            <div id="add driver">
                <c:if test="${isDriverListEmpty == true}">
                    <h3><fmt:message key="message.empty.driver.list"/></h3>
                </c:if>

                <c:if test="${isDriverListEmpty == false}">
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
                                    <td>${driver.originFirstName}</td>
                                    <td>${driver.originLastName}</td>
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