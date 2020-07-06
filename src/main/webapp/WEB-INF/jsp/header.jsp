<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="${sessionScope.lang}" scope="application"/>
<fmt:setBundle basename="application" var="message"/>

<header>
    <nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-top">
        <div class="dropdown">
            <button type="button" class="btn btn-light dropdown-toggle" data-toggle="dropdown">
                <c:out value="${sessionScope.lang == null ? 'en' : sessionScope.lang}"/>
            </button>
            <div class="dropdown-menu">
                <a class="dropdown-item" href="?locale=en">en</a>
                <a class="dropdown-item" href="?locale=ru">ru</a>
                <a class="dropdown-item" href="?locale=uk">uk</a>
            </div>
        </div>
        <div class="logo">
            <a href="/" class="nav-link text-light">
                <img src="img/train.png"/>
                <span><fmt:message bundle="${message}" key="booking.title"/></span>
            </a>
        </div>

        <c:choose>
            <c:when test="${not empty sessionScope.user}">
                <div class="btn-group btn-group-lg">
                    <div class="btn-group">
                        <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown">
                            <span>
                                ${not empty total ? total : '0.00'} <fmt:message bundle="${message}" key="booking.uah"/>
                            </span><br/>
                            <span>
                                ${not empty cart ? cart.size() : 0} <fmt:message bundle="${message}" key="booking.tickets.small"/>
                            </span>
                        </button>
                        <div class="dropdown-menu">
                            <c:forEach items="${sessionScope.cart.values()}" var="ticket">
                                <span class="dropdown-item">${ticket.tripDto.departureCity} -
                                        ${ticket.tripDto.arrivalCity}: ${ticket.ticket.price}
                                    <fmt:message bundle="${message}" key="booking.uah"/>
                                </span>
                                <div class="dropdown-divider"></div>
                            </c:forEach>
                            <span class="dropdown-item">
                                <fmt:message bundle="${message}" key="booking.total"/>: ${sessionScope.total}
                                <fmt:message bundle="${message}" key="booking.uah"/>
                            </span>
                        </div>
                    </div>

                    <button type="button" class="btn btn-info">
                        <a href="cart" class="nav-link text-light">
                            <fmt:message bundle="${message}" key="booking.checkout"/>
                        </a>
                    </button>
                </div>

                <c:if test="${user.accountStatus == 'ADMIN'}">
                    <a type="button" class="btn btn-light btn-lg" href="/admin">
                        <fmt:message bundle="${message}" key="booking.admin"/>
                    </a>
                </c:if>
                <a type="button" class="btn btn-light btn-lg" href="/logout">
                    <fmt:message bundle="${message}" key="booking.logout"/>
                </a>
            </c:when>
            <c:otherwise>
                <a type="button" class="btn btn-light btn-lg" href="/login">
                    <fmt:message bundle="${message}" key="booking.login"/>
                </a>
            </c:otherwise>
        </c:choose>
    </nav>
</header>
