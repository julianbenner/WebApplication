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
        <a href="admincp.do?action=changebook&id=<c:out value="${book.id}" />">Edit book</a>
    </jsp:attribute>
    <jsp:attribute name="footer">

    </jsp:attribute>
    <jsp:body>
        <t:question message="You're about to delete the following book. This process can't be undone. Are you sure?">
            <t:button label="Yes, delete" link="?id=${book.id}&confirm=1" type="btnNegative"/>
            <t:button label="No, keep" link="book.do?id=${book.id}"/>
        </t:question>
        <br/>
        <t:bookdetail book="${book}"/>
    </jsp:body>
</t:genericpage>