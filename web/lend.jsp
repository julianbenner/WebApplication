<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="title">
        Index
    </jsp:attribute>
    <jsp:attribute name="footer">
      <p id="copyright"></p>
    </jsp:attribute>
    <jsp:attribute name="notificationBody">
        <c:if test="${status.statusType != 'FAIL'}">
            <t:button label="Sounds good!" link="book.do?id=${bookId}" type="btnSubmit"/>
            <t:button label="Undo" link="unlend.do?id=${bookId}"/>
        </c:if>
    </jsp:attribute>
    <jsp:body>
    </jsp:body>
</t:genericpage>