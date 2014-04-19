<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="Detailed book view" pageEncoding="UTF-8" %>
<%@attribute name="book" required="true" type="main.Book" %>

<div class="logicalGroup">
            <span class="author">
                <c:forEach items="${book.authors}" var="current">
                    <a href="<c:out value="${current.id}" />"><c:out value="${current.surname}"/>, <c:out
                            value="${current.firstname}"/></a>
                </c:forEach>
            </span><br/>
    <span class="title"><c:out value="${book.title}"/> (<c:out value="${book.publisher}"/>)</span>
</div>
<div class="logicalGroup">
    ISBN: <c:out value="${book.isbn}"/>
    <hr/>
    Description<br/>
    <c:out value="${book.description}"/>
</div>