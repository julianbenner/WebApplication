<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:browse>
    <jsp:attribute name="title">
      User Control Panel
    </jsp:attribute>
    <jsp:attribute name="header">

    </jsp:attribute>
    <jsp:attribute name="footer">

    </jsp:attribute>
    <jsp:body>
        <c:forEach items="${books}" var="book">
            <div class="logicalGroup">
                <span class="author">
                    <c:forEach items="${book.authors}" var="current">
                        <a href="<c:out value="${current.id}" />"><c:out value="${current.surname}" />, <c:out value="${current.firstname}" /></a>
                    </c:forEach>
                </span><br />
                <span class="title"><a href="book.do?id=<c:out value="${book.id}" />"><c:out value="${book.title}"/></a></span>
            </div>
        </c:forEach>
    </jsp:body>
</t:browse>