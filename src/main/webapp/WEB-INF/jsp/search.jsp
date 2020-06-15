<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="${sessionScope.lang}" scope="application"/>
<fmt:setBundle basename="application" var="message"/>

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
