<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${sessionScope.get('lang')}"/>
<%--<fmt:setLocale value="${param.lang}"/>--%>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html>
<head>
    <title>
        <fmt:message key="app.name"/>
    </title>
    <div class="col-md-12 col-md-offset-10" align="right">
        <div class="locale">
            <a href="?lang=en">EN</a>|<a href="?lang=uk">UA</a>
        </div>
    </div>
</head>
<body>
<div class="container" style="margin-top: 50px">
    <div class="row">
        <div class="col-md-4 col-md-offset-2">
            <h2 class="page-header" style="color: forestgreen">
                <fmt:message key="login.page.header"/>
            </h2>
            <div>
                <form action="/login" method="post">
                    <fieldset>
                        <%--                        <div th:if="${param.error}" class="alert alert-error" th:text="#{invalid.email.password}"--%>
                        <%--                             style="margin-top:20px" th:bgcolor="red"></div>--%>
                        <%--                        <div th:if="${param.logout}" class="alert alert-success">--%>
                        <%--                            You have been logged out.--%>
                        <%--                        </div>--%>
                        <label for="email">
                            <fmt:message key="email"/>
                        </label>
                        <input type="text" id="email" name="email" placeholder='<fmt:message key="email"/>'
                               class="form-control"/>
                        <label for="password">
                            <fmt:message key="password"/>
                        </label>
                        <input type="password" id="password" name="password" placeholder='<fmt:message key="password"/>'
                               class="form-control"
                               placeholder="Password"/>
                        <div class="form-actions">
                            <input type="submit" value='<fmt:message key="sign.in"/>' class="btn btn-default"
                                   style="margin-top:20px">
                        </div>
                    </fieldset>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>