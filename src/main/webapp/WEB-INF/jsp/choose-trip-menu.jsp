<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="${sessionScope.lang}" scope="application"/>
<fmt:setBundle basename="application" var="message"/>

<div class="container">
    <div class="row">
        <div class="col-md-6">
            <form method="get" action="/admin/chooseTripDate">
                <div class="form-group">
                    <label for="date"><fmt:message bundle="${message}" key="booking.choose.trip.date"/>:</label>
                    <input id="date" type="date" class="form-control" name="date" value="${sessionScope.date}">
                </div>
                <button class="btn btn-lg btn-info btn-block" type="submit">
                    <fmt:message bundle="${message}" key="booking.choose.date"/>
                </button>
            </form>
        </div>

        <div class="col-md-6">
            <form method="get" action="/admin/chooseTrip">
                <div class="form-group">
                    <label for="trip_id"><fmt:message bundle="${message}" key="booking.choose.trip"/>:</label>
                    <select id="trip_id" name="trip_id" class="form-control">
                        <c:forEach items="${sessionScope.trips}" var="trip">
                            <option value="${trip.trip.tripId}"
                                ${trip.trip.tripId == sessionScope.selectedTrip.tripId ? 'selected' : ''}>
                                Train №${trip.train.trainNumber} ${trip.departureCity} - ${trip.arrivalCity}:
                                    ${trip.trip.departureTime}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <button class="btn btn-lg btn-info btn-block" type="submit">
                    <fmt:message bundle="${message}" key="booking.choose.trip"/>
                </button>
            </form>
        </div>
    </div>
</div>
