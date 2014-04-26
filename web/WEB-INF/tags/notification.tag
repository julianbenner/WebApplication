<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="Template for notifications" pageEncoding="UTF-8" %>
<%@attribute name="message" required="true" type="java.lang.String" %>
<%@attribute name="type" required="true" type="java.lang.String" %>
<%@attribute name="icon" required="true" type="java.lang.String" %>

<div class="logicalGroup">
    <div class="icon <c:out value="${type}" />"><c:out value="${icon}"/></div>
    <div class="notificationMessage"><c:out value="${message}"/></div>
    <div style="width:699px;margin-left:60px;text-align: center">
        <jsp:doBody/>
    </div>
</div>