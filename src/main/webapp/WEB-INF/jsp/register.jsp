<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setBundle basename="application" var="message"/>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/normalize.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/main.css" charset="UTF-8">
    <title>Register</title>
</head>
<body>
    <div class="register">
        <div class="container">
            <h2>Register</h2>
            <form action="register" method="post" id="registerForm">
                <div class="form-group">
                    <label for="first-name">First Name:</label>
                    <input type="text" id="first-name" class="form-control"
                           placeholder="Enter First Name"
                           required="required"
                           name="firstName">
                </div>
                <div class="form-group">
                    <label for="last-name">Last Name:</label>
                    <input type="text" id="last-name" class="form-control"
                           placeholder="Enter Last Name"
                           required="required"
                           name="lastName">
                </div>
                <div class="form-group">
                    <label for="phone">Phone:</label>
                    <input type="tel" id="phone" class="form-control"
                           placeholder="Format: 123-456-78-90"
                           pattern="[0-9]{3}-[0-9]{3}-[0-9]{2}-[0-9]{2}"
                           required="required"
                           name="phone">
                </div>
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" id="email" class="form-control"
                           placeholder="Enter Email"
                           required="required"
                           name="email">
                </div>
                <div class="form-group">
                    <label for="card-number">Card Number:</label>
                    <input type="number" id="card-number" class="form-control"
                           placeholder="Enter Card Number"
                           min="1000000000" max="9999999999"
                           required="required"
                           name="cardNumber">
                </div>
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" id="username" class="form-control"
                           placeholder="Enter username"
                           required="required"
                           minlength="3"
                           name="userName">
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" id="password" class="form-control"
                           placeholder="Enter password"
                           required="required"
                           minlength="5"
                           name="password">
                </div>
                <button type="submit" id="userRegister" class="btn btn-info btn-block">
                    Register
                </button>
            </form>
            <a class="btn btn-dark btn-block" href="/">Home</a>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>



