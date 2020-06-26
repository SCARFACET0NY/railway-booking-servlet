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
    <title><fmt:message bundle="${message}" key="booking.cart"/></title>
</head>
<body>
    <div class="container-fluid cart">
        <h2 class="cart-header"><fmt:message bundle="${message}" key="booking.tickets"/></h2>

        <c:if test="${not empty cart}">
            <fmt:parseDate  value="${trip.departureTime}" type="date" pattern="yyyy-MM-dd'T'HH:mm" var="departureTime"/>
            <fmt:parseDate  value="${trip.arrivalTime}" type="date" pattern="yyyy-MM-dd'T'HH:mm" var="arrivalTime"/>
            <table class="table table-hover">
                <tr class="bg-info text-dark">
                    <th><fmt:message bundle="${message}" key="booking.ticket.train.number"/></th>
                    <th><fmt:message bundle="${message}" key="booking.ticket.city.from"/></th>
                    <th><fmt:message bundle="${message}" key="booking.ticket.city.to"/></th>
                    <th><fmt:message bundle="${message}" key="booking.ticket.time.departure"/></th>
                    <th><fmt:message bundle="${message}" key="booking.ticket.time.arrival"/></th>
                    <th><fmt:message bundle="${message}" key="booking.ticket.wagon.number"/></th>
                    <th><fmt:message bundle="${message}" key="booking.ticket.seat.number"/></th>
                    <th><fmt:message bundle="${message}" key="booking.ticket.price"/></th>
                    <th><fmt:message bundle="${message}" key="booking.ticket.action"/></th>
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
                                <button type="submit" class="btn btn-info">
                                    <fmt:message bundle="${message}" key="booking.ticket.remove"/>
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <p class="total"><fmt:message bundle="${message}" key="booking.cart.total"/>: ${sessionScope.total}</p>

            <form action="pay" method="post">
                <button type="submit" class="btn btn-lg btn-info btn-block">
                    <fmt:message bundle="${message}" key="booking.cart.pay"/>
                </button>
            </form>
        </c:if>

        <a class="btn btn-lg btn-dark btn-block home" href="/">
            <fmt:message bundle="${message}" key="booking.home"/>
        </a>
    </div>
</body>
</html>


