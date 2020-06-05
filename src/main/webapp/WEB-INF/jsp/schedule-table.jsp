<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="${sessionScope.lang}" scope="application"/>
<fmt:setBundle basename="application" var="message"/>

<table class="table table-hover">
    <thead class="bg-info text-dark">
        <tr>
            <th>Train Number</th>
            <th>From</th>
            <th>To</th>
            <th>Departure</th>
            <th>Arrival</th>
            <th>Prices from</th>
            <th>Action</th>
        </tr>
    </thead>
    <tbody class="bg-dark text-light">
        <c:forEach items="${trips}" var="trip">
            <fmt:parseDate  value="${trip.departureTime}" type="date" pattern="yyyy-MM-dd'T'HH:mm" var="departureTime"/>
            <fmt:parseDate  value="${trip.arrivalTime}" type="date" pattern="yyyy-MM-dd'T'HH:mm" var="arrivalTime"/>
            <tr>
                <td>${trip.trainNumber}</td>
                <td>${trip.departureCity}</td>
                <td>${trip.arrivalCity}</td>
                <td><fmt:formatDate value="${departureTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                <td><fmt:formatDate value="${arrivalTime}" pattern="yyyy-MM-dd HH:mm"/></td>
                <td>${trip.minPrice}</td>
                <td>
                    <form method="get" action="/setTrip">
                        <input type="hidden" name="trip_id" value="${trip.trip.tripId}">
                        <button class="btn btn-info" type="submit">Buy Ticket</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>