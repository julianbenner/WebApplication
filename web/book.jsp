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

    </jsp:attribute>
    <jsp:attribute name="footer">

    </jsp:attribute>
    <jsp:body>
        <t:bookdetail book="${book}"/>
        <div style="margin-top: 10px">
        <t:button label="Lend" link="lend.do?id=${book.id}" type="btnSubmit"/>
        <c:if test="${user.group == 1 || user.group == 2}">
            <t:button label="Edit" link="admincp.do?action=changebook&id=${book.id}"/>
            <t:button label="Delete" link="delete_book.do?id=${book.id}" type="btnNegative"/>
        </c:if>
        </div>
    </jsp:body>
</t:genericpage>