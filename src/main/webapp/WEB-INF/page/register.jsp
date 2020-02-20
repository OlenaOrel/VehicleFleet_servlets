<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html>
<head>
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
            <div class="col-md-10 col-md-offset-9" align="right">
                <div class="locale">
                    <a href="?lang=en">EN</a>|<a href="?lang=uk">UA</a>
                </div>
            </div>
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
            <div class="error" style="margin-top:20px" bgcolor="red">
                <c:if test="${error == true}">
                    <fmt:message key="message.register.error"/>
                </c:if>
                <c:if test="${passNotConfirm == true}">
                    <fmt:message key="message.register.pass.not.confirm"/>
                </c:if>
                <c:if test="${invalidInput == true}">
                    <fmt:message key="message.register.invalid.input"/>
                </c:if>
            </div>
            <div>
                <form action="${pageContext.request.contextPath}/register" method="post">
                    <fieldset>
                        <label for="firstName" style="margin-top:20px">First name</label>
                        <input type="text" id="firstName" name="firstName"
                               placeholder="First name"
                               class="form-control"/>
                        <label for="lastName" style="margin-top:20px">Last name</label>
                        <input type="text" id="lastName" name="lastName"
                               placeholder="First name"
                               class="form-control"/>
                        <label for="originFirstName" style="margin-top:20px">
                            <fmt:message key="message.user.origin.first.name"/>
                        </label>
                        <input type="text" id="originFirstName" name="originFirstName"
                               placeholder='<fmt:message key="message.user.origin.first.name"/>'
                               class="form-control"/>
                        <label for="originLastName" style="margin-top:20px">
                            <fmt:message key="message.user.origin.last.name"/>
                        </label>
                        <input type="text" id="originLastName" name="originLastName"
                               placeholder='<fmt:message key="message.user.origin.last.name"/>'
                               class="form-control"/>
                        <label for="email" style="margin-top:20px">
                            <fmt:message key="message.user.email"/>
                        </label>
                        <input type="text" id="email" name="email"
                               placeholder='<fmt:message key="message.user.email"/>'
                               class="form-control"/>
                        <label for="password" style="margin-top:20px">
                            <fmt:message key="message.user.password"/>
                        </label>
                        <input type="password" id="password" name="pass"
                               placeholder='<fmt:message key="message.user.password"/>'
                               class="form-control"
                               placeholder="Password"/>
                        <label for="confirmPass" style="margin-top:20px">
                            <fmt:message key="message.user.confirm.password"/>
                        </label>
                        <input type="password" id="confirmPass" name="confirmPass"
                               placeholder='<fmt:message key="message.user.confirm.password"/>'
                               class="form-control"
                               placeholder="Password"/>
                        <div class="form-actions">
                            <input type="submit" value='<fmt:message key="message.sign.up"/>'
                                   class="btn btn-default" style="margin-top:20px">
                        </div>
                    </fieldset>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>