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
    <title><fmt:message bundle="${message}" key="booking.trip"/></title>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <jsp:include page="sub-header.jsp"/>

    <c:if test="${not empty sessionScope.trip}">
        <fmt:parseDate  value="${trip.departureTime}" type="date" pattern="yyyy-MM-dd'T'HH:mm" var="departureTime"/>
        <fmt:parseDate  value="${trip.arrivalTime}" type="date" pattern="yyyy-MM-dd'T'HH:mm" var="arrivalTime"/>
        <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <form method="get" action="/setWagonClass">
                        <div class="form-group">
                            <label for="wagon_class"><fmt:message bundle="${message}" key="booking.ticket.wagon.class"/>:</label>
                            <select id="wagon_class" name="wagon_class" class="form-control">
                                <c:forEach items="${sessionScope.wagonClasses}" var="wagonClass">
                                    <option value="${wagonClass}"
                                        ${wagonClass.equals(sessionScope.selectedWagonClass) ? 'selected' : ''}>
                                            ${wagonClass}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        <button class="btn btn-lg btn-info btn-block" type="submit">
                            <fmt:message bundle="${message}" key="booking.choose.class"/>
                        </button>
                    </form>
                </div>
                <div class="col-md-4">
                    <c:if test="${not empty sessionScope.wagons}">
                        <form method="get" action="/setWagon">
                            <div class="form-group">
                                <label for="wagon_id"><fmt:message bundle="${message}" key="booking.ticket.wagon.number"/>:</label>
                                <select id="wagon_id" name="wagon_id" class="form-control">
                                    <c:forEach items="${sessionScope.wagons}" var="wagon">
                                        <option value="${wagon.wagonId}"
                                            ${wagon.wagonId == sessionScope.selectedWagon.wagonId ? 'selected' : ''}>
                                                ${wagon.wagonNumber}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <button class="btn btn-lg btn-info btn-block" type="submit">
                                <fmt:message bundle="${message}" key="booking.choose.wagon"/>
                            </button>
                        </form>
                    </c:if>
                </div>
                <div class="col-md-4">
                    <c:if test="${not empty sessionScope.seats}">
                        <form method="get" action="/setSeat">
                            <div class="form-group">
                                <label for="seat_id"><fmt:message bundle="${message}" key="booking.ticket.seat.number"/>:</label>
                                <select id="seat_id" name="seat_id" class="form-control">
                                    <c:forEach items="${sessionScope.seats}" var="seat">
                                        <option value="${seat.tripSeat.tripSeatId}"
                                            ${seat.tripSeat.tripSeatId == sessionScope.selectedSeat.tripSeat.tripSeatId
                                            ? 'selected' : ''}>${seat.seatNumber}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <button class="btn btn-lg btn-info btn-block" type="submit">
                                <fmt:message bundle="${message}" key="booking.choose.seat"/>
                            </button>
                        </form>
                    </c:if>
                </div>
            </div>
            <div class="show-ticket">
                <c:if test="${not empty sessionScope.selectedSeat}">
                    <form method="get" action="/showTicket">
                        <button type="submit" class="btn btn-lg btn-dark btn-block">
                            <fmt:message bundle="${message}" key="booking.ticket.show"/>
                        </button>
                    </form>
                </c:if>
            </div>
        </div>
    </c:if>

    <c:if test="${not empty sessionScope.ticket}">
        <div class="container-fluid ticket">
            <table class="table table-hover">
                <thead class="bg-info text-dark">
                    <tr>
                        <th><fmt:message bundle="${message}" key="booking.ticket.train.number"/></th>
                        <th><fmt:message bundle="${message}" key="booking.ticket.city.from"/></th>
                        <th><fmt:message bundle="${message}" key="booking.ticket.city.to"/></th>
                        <th><fmt:message bundle="${message}" key="booking.ticket.time.departure"/></th>
                        <th><fmt:message bundle="${message}" key="booking.ticket.time.arrival"/></th>
                        <th><fmt:message bundle="${message}" key="booking.trip.duration"/></th>
                        <th><fmt:message bundle="${message}" key="booking.ticket.wagon.number"/></th>
                        <th><fmt:message bundle="${message}" key="booking.ticket.seat.number"/></th>
                        <th><fmt:message bundle="${message}" key="booking.ticket.price"/></th>
                        <th><fmt:message bundle="${message}" key="booking.ticket.action"/></th>
                    </tr>
                </thead>
                <tbody class="bg-dark text-light">
                    <tr>
                        <td>${sessionScope.trip.train.trainNumber}</td>
                        <td>${sessionScope.trip.departureCity}</td>
                        <td>${sessionScope.trip.arrivalCity}</td>
                        <td><fmt:formatDate value="${departureTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                        <td><fmt:formatDate value="${arrivalTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                        <td>${sessionScope.trip.durationInMinutes}</td>
                        <td>${sessionScope.selectedWagon.wagonNumber}</td>
                        <td>${sessionScope.selectedSeat.seatNumber}</td>
                        <td>${sessionScope.ticket.price}</td>
                        <td>
                            <form method="post" action="/addTicket">
                                <button type="submit" class="btn btn-info">
                                    <fmt:message bundle="${message}" key="booking.cart.add"/>
                                </button>
                            </form>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </c:if>

    <jsp:include page="footer.jsp"/>

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>


