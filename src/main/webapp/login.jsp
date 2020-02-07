<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${sessionScope.get('lang')}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html>
<head>
    <title>
        <fmt:message key="message.app.name"/>
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
                <fmt:message key="message.login.page.header"/>
            </h2>
            <div>
                <form action="${pageContext.request.contextPath}/login" method="post">
                    <fieldset>
                        <label for="email">
                            <fmt:message key="message.email"/>
                        </label>
                        <input type="text" id="email" name="email" placeholder='<fmt:message key="message.email"/>'
                               class="form-control"/>
                        <label for="password">
                            <fmt:message key="message.password"/>
                        </label>
                        <input type="password" id="password" name="pass"
                               placeholder='<fmt:message key="message.password"/>'
                               class="form-control"
                               placeholder="Password"/>
                        <div class="form-actions">
                            <input type="submit" class="btn btn-default"
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