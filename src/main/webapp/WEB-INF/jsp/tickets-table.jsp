<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${not empty sessionScope.paidTickets}">
    <div class="container-fluid ticket">
        <table class="table table-hover">
            <thead class="bg-info text-dark">
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Wagon Class</th>
                    <th>Wagon Number</th>
                    <th>Seat Number</th>
                    <th>Price</th>
                    <th>Remove</th>
                    <th>Change</th>
                </tr>
            </thead>
            <tbody class="bg-dark text-light">
                <c:forEach items="${sessionScope.paidTickets}" var="ticket" varStatus="loop">
                    <tr>
                        <td>${ticket.user.firstName}</td>
                        <td>${ticket.user.lastName}</td>
                        <td>${ticket.user.email}</td>
                        <td>${ticket.wagonClass}</td>
                        <td>${ticket.wagon.wagonNumber}</td>
                        <td>${ticket.seat.seatNumber}</td>
                        <td>${ticket.ticket.price}</td>
                        <td>
                            <form method="post" action="/admin/cancelTicket">
                                <input type="hidden" name="ticket_id" value="${ticket.ticket.ticketId}">
                                <button type="submit" class="btn btn-info">Cancel Ticket</button>
                            </form>
                        </td>
                        <td>
                            <a type="submit" role="button" class="btn btn-info"
                               data-toggle="collapse" href="#collapse${loop.index}">Change Place</a>
                        </td>
                    </tr>
                    <tr id="collapse${loop.index}" class="collapse ${loop.index == sessionScope.index ? 'show' : ''}">
                        <jsp:include page="place-change-dropdown.jsp"/>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <jsp:include page="pagination.jsp"/>
    </div>
</c:if>