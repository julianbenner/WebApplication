<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="referer1" class="java.lang.String" scope="request"/>

<t:genericpage>
    <jsp:attribute name="title">
        Login
    </jsp:attribute>
    <jsp:attribute name="notificationBody">
        Click <a href="<c:out value="${referer1}"/>">here</a> to get back to your last page.
    </jsp:attribute>
</t:genericpage>