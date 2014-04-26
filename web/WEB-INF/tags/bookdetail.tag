<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="Detailed book view" pageEncoding="UTF-8" %>
<%@attribute name="book" required="true" type="main.Book" %>
<%@attribute name="showTitle" required="false" type="java.lang.Boolean" %>

<c:if test="${(showTitle != null && showTitle != false) || showTitle}">
    <div class="logicalGroup">
            <span class="author">
                <c:forEach items="${book.authors}" var="current">
                    <a href="author.do?id=<c:out value="${current.id}" />"><c:out value="${current.surname}"/>, <c:out
                            value="${current.firstname}"/></a>
                </c:forEach>
            </span><br/>
    <span class="title"><c:out value="${book.title}"/> (<c:out value="${book.publisher}"/>)</span>
</div>
</c:if>
<div class="logicalGroup">

    <c:choose> <c:when test="${book.available}">
        This book is currently available!
    </c:when> <c:otherwise>
        Sorry, currently not available</c:otherwise>
    </c:choose>
    <hr/>
    <span class="smallEmphasis">Isbn</span><br/>
    <c:out value="${book.isbn}"/>
    <hr/>
    <span class="smallEmphasis">Description</span><br/>
    <c:out value="${book.description}"/>
</div>