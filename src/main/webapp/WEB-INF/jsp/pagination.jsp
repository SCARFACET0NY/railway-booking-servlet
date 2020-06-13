<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setBundle basename="application" var="message"/>

<c:if test="${not empty sessionScope.paidTickets}">
    <ul class="pagination">
        <li class="page-item">
            <a class="page-link" <c:out value="${sessionScope.page > 0 ? 'href' : ''}" />
            ="${request.requestURL}?page=${sessionScope.page - 1}">Previous</a>
        </li>
        <c:forEach begin="0" end="${sessionScope.numOfPages}" varStatus="page">
            <li class="page-item">
                <a class="page-link <c:out value="${page.index == sessionScope.page ? 'active' : ''}" />"
                   href="${request.requestURL}?page=${page.index}">${page.index + 1}</a>
            </li>
        </c:forEach>
        <li class="page-item ">
            <a class="page-link" <c:out value="${sessionScope.page < sessionScope.numOfPages ? 'href' : ''}" />
            ="${request.requestURL}?page=${sessionScope.page + 1}">Next</a>
        </li>
    </ul>
</c:if>
