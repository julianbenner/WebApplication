<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="user" class="main.User" scope="request"/>
<jsp:useBean id="book" class="main.Book" scope="request"/>
<jsp:useBean id="showTitle" class="java.lang.Boolean" scope="request"/>

<t:genericpage>
    <jsp:attribute name="title">
      <c:out value="${book.title}"/>
    </jsp:attribute>

    <jsp:body>
        <t:book user="${user}" book="${book}" showTitle="${showTitle}"/>
    </jsp:body>
</t:genericpage>