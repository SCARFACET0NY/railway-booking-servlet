<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="${sessionScope.lang}" scope="application"/>
<fmt:setBundle basename="application" var="message"/>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/normalize.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <title><fmt:message bundle="${message}" key="booking.title"/></title>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <jsp:include page="sub-header.jsp"/>

    <div class="search">
        <div class="container">
            <h2><fmt:message bundle="${message}" key="booking.search.trains"/></h2>
            <form method="get" action="/search">
                <div class="row">
                    <div class="col-md-3">
                        <label for="departure">Departure City:</label>
                        <input id="departure" type="text" class="form-control" name="departure">
                    </div>
                    <div class="col-md-3">
                        <label for="arrival">Arrival City:</label>
                        <input id="arrival" type="text" class="form-control" name="arrival">
                    </div>
                    <div class="col-md-3">
                        <label for="date">Date:</label>
                        <input id="date" type="date" class="form-control" name="date">
                    </div>
                    <div class="col-md-3">
                        <button type="submit" class="btn btn-lg btn-info form-control">
                            <fmt:message bundle="${message}" key="booking.search"/>
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>


    <c:if test="${not empty trips}">
        <div class="container">
            <h2>Trains</h2>
            <jsp:include page="schedule-table.jsp"/>
        </div>
    </c:if>

    <jsp:include page="footer.jsp"/>

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>
