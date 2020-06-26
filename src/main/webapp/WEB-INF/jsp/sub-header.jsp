<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.lang}" scope="application"/>
<fmt:setBundle basename="application" var="message"/>
<nav class="navbar navbar-expand-sm bg-info justify-content-center sub-header">
    <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link ${requestScope['javax.servlet.forward.request_uri'].equals("/schedule")
                                 ? "text-light" : ""}" href="/schedule">
                <img class="icon" src="img/schedule_icon.png"/>
                <span><fmt:message bundle="${message}" key="booking.schedule"/></span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link ${requestScope['javax.servlet.forward.request_uri'].equals("/")
                                 ? "text-light" : ""}" href="/">
                <img class="icon" src="img/search_icon.png"/>
                <span><fmt:message bundle="${message}" key="booking.search"/></span>
            </a>
        </li>
    </ul>
</nav>
