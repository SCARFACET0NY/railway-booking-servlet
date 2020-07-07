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
                    <label for="departure"><fmt:message bundle="${message}" key="booking.search.departure"/>:</label>
                    <input id="departure" type="text" class="form-control" name="departure" required="required">
                </div>
                <div class="col-md-3">
                    <label for="arrival"><fmt:message bundle="${message}" key="booking.search.arrival"/>:</label>
                    <input id="arrival" type="text" class="form-control" name="arrival" required="required">
                </div>
                <div class="col-md-3">
                    <label for="date"><fmt:message bundle="${message}" key="booking.search.date"/>:</label>
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
        <h2><fmt:message bundle="${message}" key="booking.trains"/></h2>
        <jsp:include page="schedule-table.jsp"/>
    </div>
</c:if>
<c:if test="${requestScope.trips.size() == 0}">
    <h2><fmt:message bundle="${message}" key="booking.search.empty"/></h2>
</c:if>
