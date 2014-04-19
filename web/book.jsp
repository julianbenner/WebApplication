<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="user" class="main.User" scope="request"/>
<jsp:useBean id="book" class="main.Book" scope="request"/>

<t:genericpage>
    <jsp:attribute name="title">
      User Control Panel
    </jsp:attribute>
    <jsp:attribute name="header">
        <c:if test="${user.group == 1 || user.group == 2}"><a
                href="admincp.do?action=changebook&id=<c:out value="${book.id}" />">Edit book</a> | <a
                href="delete_book.do?id=<c:out value="${book.id}" />">Delete book</a></c:if>
    </jsp:attribute>
    <jsp:attribute name="footer">

    </jsp:attribute>
    <jsp:body>
        <t:bookdetail book="${book}"/>
    </jsp:body>
</t:genericpage>