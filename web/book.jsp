<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="title">
      User Control Panel
    </jsp:attribute>
    <jsp:attribute name="header">

    </jsp:attribute>
    <jsp:attribute name="footer">

    </jsp:attribute>
    <jsp:body>
        <span class="author"><c:out value="${book.author}"/></span><br />
        <span class="title"><c:out value="${book.title}"/></span>
        <c:choose>
            <c:when test="${sessionScope.user != null}">

            </c:when>
            <c:otherwise>

            </c:otherwise>
        </c:choose>
    </jsp:body>
</t:genericpage>