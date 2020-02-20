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
            <div id="add_bus">
                <c:if test="${isBusListEmpty == true}">
                    <h3><fmt:message key="message.empty.bus.list"/></h3>
                </c:if>
                <c:if test="${isBusListEmpty == false}">
                    <table class="table">
                        <tr>
                            <th>
                                <fmt:message key="message.bus.license.plate"/>
                            </th>
                            <th>
                                <fmt:message key="message.bus.mark"/>
                            </th>
                            <th>
                                <fmt:message key="message.bus.add"/>
                            </th>
                        </tr>

                        <c:forEach items="${busList}" var="bus">
                            <tr>
                                <td>${bus.licensePlate}</td>
                                <td>${bus.mark}</td>
                                <td>
                                    <form action="${pageContext.request.contextPath}/admin/appoint/bus"
                                          method="post">
                                        <input type="hidden" name="busId" value="${bus.id}">
                                        <input type="submit" value='<fmt:message key="message.bus.add"/>'
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