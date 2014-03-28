<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="title">
      User Control Panel
    </jsp:attribute>
    <jsp:attribute name="header">
        <c:if test="${user.group == 1 || user.group == 2}"><a href="admincp.do?action=changebook&id=<c:out value="${book.id}" />">Edit book</a></c:if>
    </jsp:attribute>
    <jsp:attribute name="footer">

    </jsp:attribute>
    <jsp:body>
        <div class="logicalGroup">
            <span class="author">
                <c:forEach items="${book.authors}" var="current">
                    <a href="<c:out value="${current.id}" />"><c:out value="${current.surname}" />, <c:out value="${current.firstname}" /></a>
                </c:forEach>
            </span><br />
            <span class="title"><c:out value="${book.title}"/> (<c:out value="${book.publisher}"/>)</span>
        </div>
        <div class="logicalGroup">
            Description<br />
            ISBN: <c:out value="${book.isbn}"/>
            <hr />
            <c:out value="${book.description}"/>
        </div>
        <c:choose>
            <c:when test="${sessionScope.user != null}">

            </c:when>
            <c:otherwise>

            </c:otherwise>
        </c:choose>
    </jsp:body>
</t:genericpage>