<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="${sessionScope.lang}" scope="application"/>
<fmt:setBundle basename="application" var="message"/>
<td colspan="9">
    <div class="row">
        <div class="col-md-3">
            <form method="post" action="/admin/chooseWagonClass">
                <input type="hidden" name="index" value="${loop.index}">
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
        <div class="col-md-3">
            <c:if test="${not empty sessionScope.wagons}">
                <form method="post" action="/admin/chooseWagon">
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
        <div class="col-md-3">
            <c:if test="${not empty sessionScope.seats}">
                <form method="post" action="/admin/chooseSeat">
                    <input type="hidden" name="ticket_id" value="${ticket.ticket.ticketId}">
                    <input type="hidden" name="old_wagon_id" value="${ticket.wagon.wagonId}">
                    <input type="hidden" name="old_seat_id" value="${ticket.tripSeat.tripSeatId}">
                    <div class="form-group">
                        <label for="seat_id"><fmt:message bundle="${message}" key="booking.ticket.seat.number"/>:</label>
                        <select id="seat_id" name="seat_id" class="form-control">
                            <c:forEach items="${sessionScope.seats}" var="seat">
                                <option value="${seat.tripSeat.tripSeatId}"
                                    ${seat.tripSeat.tripSeatId ==
                                            sessionScope.selectedSeat.tripSeat.tripSeatId ? 'selected' : ''}>
                                        ${seat.seatNumber}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <button class="btn btn-lg btn-info btn-block" type="submit">
                        <fmt:message bundle="${message}" key="booking.choose.seat"/>
                    </button>
                </form>
            </c:if>
        </div>
        <div class="col-md-3">
            <form method="get" action="/admin/cancelSeatChange">
                <button class="btn btn-lg btn-info btn-block cancel" type="submit">
                    <fmt:message bundle="${message}" key="booking.cancel"/>
                </button>
            </form>
        </div>
    </div>
</td>
