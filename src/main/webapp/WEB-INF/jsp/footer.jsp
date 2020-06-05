<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="${sessionScope.lang}" scope="application"/>
<fmt:setBundle basename="application" var="message"/>

<footer>
    <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
        <div class="logo">
            <a href="/" class="nav-link text-light">
                <img src="img/train.png" width="32" height="32"/>
                <span><fmt:message bundle="${message}" key="booking.title"/></span>
            </a>
        </div>
        <div class="copyright">
        <span class="text-light">
            © 2020 <fmt:message bundle="${message}" key="booking.developer"/>:
            <fmt:message bundle="${message}" key="booking.author"/>
        </span>
        </div>
        <a class="github text-light nav-link" href="https://github.com/SCARFACET0NY/railway-booking-servlet">
            <i class="fa fa-github" style="font-size: 32px;"></i>
            <span><fmt:message bundle="${message}" key="booking.github"/></span>
        </a>
    </nav>
</footer>