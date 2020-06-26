<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setBundle basename="application" var="message"/>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/normalize.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/main.css" charset="UTF-8">
    <title><fmt:message bundle="${message}" key="booking.register"/></title>
</head>
<body>
    <div class="register">
        <div class="container">
            <h2><fmt:message bundle="${message}" key="booking.register"/></h2>
            <form action="register" method="post" id="registerForm">
                <div class="form-group">
                    <label for="first-name"><fmt:message bundle="${message}" key="booking.register.first"/>:</label>
                    <input type="text" id="first-name" class="form-control"
                           placeholder="Enter First Name"
                           required="required"
                           name="firstName">
                </div>
                <div class="form-group">
                    <label for="last-name"><fmt:message bundle="${message}" key="booking.register.last"/>:</label>
                    <input type="text" id="last-name" class="form-control"
                           placeholder="Enter Last Name"
                           required="required"
                           name="lastName">
                </div>
                <div class="form-group">
                    <label for="phone"><fmt:message bundle="${message}" key="booking.register.phone"/>:</label>
                    <input type="tel" id="phone" class="form-control"
                           placeholder="Format: 123-456-78-90"
                           pattern="[0-9]{3}-[0-9]{3}-[0-9]{2}-[0-9]{2}"
                           required="required"
                           name="phone">
                </div>
                <div class="form-group">
                    <label for="email"><fmt:message bundle="${message}" key="booking.register.email"/>:</label>
                    <input type="email" id="email" class="form-control"
                           placeholder="Enter Email"
                           required="required"
                           name="email">
                </div>
                <div class="form-group">
                    <label for="card-number"><fmt:message bundle="${message}" key="booking.register.card.number"/>:</label>
                    <input type="number" id="card-number" class="form-control"
                           placeholder="Enter Card Number"
                           min="1000000000" max="9999999999"
                           required="required"
                           name="cardNumber">
                </div>
                <div class="form-group">
                    <label for="username"><fmt:message bundle="${message}" key="booking.login.username"/>:</label>
                    <input type="text" id="username" class="form-control"
                           placeholder="Enter username"
                           required="required"
                           minlength="3"
                           name="userName">
                </div>
                <div class="form-group">
                    <label for="password"><fmt:message bundle="${message}" key="booking.login.password"/>:</label>
                    <input type="password" id="password" class="form-control"
                           placeholder="Enter password"
                           required="required"
                           minlength="5"
                           name="password">
                </div>
                <button type="submit" id="userRegister" class="btn btn-info btn-block">
                    <fmt:message bundle="${message}" key="booking.register"/>
                </button>
            </form>
            <a class="btn btn-dark btn-block" href="/">
                <fmt:message bundle="${message}" key="booking.home"/>
            </a>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>



