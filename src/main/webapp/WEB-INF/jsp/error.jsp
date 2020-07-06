<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.lang}" scope="application"/>
<fmt:setBundle basename="application" var="message"/>

<html>
<head>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" type="text/css" href="css/normalize.css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/main.css" charset="UTF-8">
    <title><fmt:message bundle="${message}" key="booking.error"/></title>
</head>
<body>
    <div class="error">
        <div class="container">
            <div class="alert alert-danger">
                <h2><fmt:message bundle="${message}" key="booking.error"/></h2>
                <c:if test="${pageContext.response.status == 404}">
                    <p><fmt:message bundle="${message}" key="booking.error.404"/></p>
                </c:if>
                <c:if test="${pageContext.response.status == 500}">
                    <p><fmt:message bundle="${message}" key="booking.error.500"/></p>
                </c:if>
            </div>
            <a class="btn btn-info btn-block" href="/"><fmt:message bundle="${message}" key="booking.home"/></a>
        </div>
    </div>
</body>
</html>