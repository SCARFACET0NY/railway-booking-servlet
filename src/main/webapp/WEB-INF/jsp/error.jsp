<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" type="text/css" href="css/normalize.css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/main.css" charset="UTF-8">
    <title>Error</title>
</head>
<body>
    <div class="error">
        <div class="container">
            <div class="alert alert-danger">
                <h2>Error</h2>
                <c:if test="${pageContext.response.status == 404}">
                    <p>Page not found</p>
                </c:if>
                <c:if test="${pageContext.response.status == 500}">
                    <p>Server error</p>
                </c:if>
            </div>
            <a class="btn btn-info btn-block" href="/">Home</a>
        </div>
    </div>
</body>
</html>