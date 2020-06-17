<%@ page contentType="text/html; charset=UTF-16" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.lang}" scope="application"/>
<fmt:setBundle basename="application" var="message"/>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/normalize.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <title>Cart</title>
</head>
<body>
    <div class="container-fluid cart">
        <h2 class="cart-header">Tickets</h2>

        <c:if test="${not empty cart}">
            <fmt:parseDate  value="${trip.departureTime}" type="date" pattern="yyyy-MM-dd'T'HH:mm" var="departureTime"/>
            <fmt:parseDate  value="${trip.arrivalTime}" type="date" pattern="yyyy-MM-dd'T'HH:mm" var="arrivalTime"/>
            <table class="table table-hover">
                <tr class="bg-info text-dark">
                    <th>Train Number</th>
                    <th>From</th>
                    <th>To</th>
                    <th>Departure</th>
                    <th>Arrival</th>
                    <th>Wagon Number</th>
                    <th>Seat Number</th>
                    <th>Price</th>
                    <th>Action</th>
                </tr>
                <c:forEach items="${cart.values()}" var="ticket">
                    <fmt:parseDate  value="${ticket.tripDto.departureTime}" type="date"
                                    pattern="yyyy-MM-dd'T'HH:mm" var="departureTime"/>
                    <fmt:parseDate  value="${ticket.tripDto.arrivalTime}" type="date"
                                    pattern="yyyy-MM-dd'T'HH:mm" var="arrivalTime"/>
                    <tr class="bg-dark text-light">
                        <td>${ticket.tripDto.train.trainNumber}</td>
                        <td>${ticket.tripDto.departureCity}</td>
                        <td>${ticket.tripDto.arrivalCity}</td>
                        <td><fmt:formatDate value="${departureTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                        <td><fmt:formatDate value="${arrivalTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                        <td>${ticket.wagonNumber}</td>
                        <td>${ticket.tripSeatDto.seatNumber}</td>
                        <td>${ticket.ticket.price}</td>
                        <td>
                            <form method="post" action="/removeTicket">
                                <input type="hidden" name="seat_id" value="${ticket.tripSeatDto.tripSeat.tripSeatId}">
                                <button type="submit" class="btn btn-info">Remove</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <p class="total">Total: ${sessionScope.total}</p>

            <form action="pay" method="post">
                <button type="submit" class="btn btn-lg btn-info btn-block">
                    Pay
                </button>
            </form>
        </c:if>

        <a class="btn btn-lg btn-dark btn-block home" href="/">
            Home
        </a>
    </div>
</body>
</html>


