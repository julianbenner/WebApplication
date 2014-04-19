<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="Template for notifications" pageEncoding="UTF-8" %>
<%@attribute name="label" required="true" type="java.lang.String" %>
<%@attribute name="link" required="true" type="java.lang.String" %>
<%@attribute name="type" required="false" type="java.lang.String" %>

<c:choose>
    <c:when test="${link eq 'submit'}">
        <input type="submit" class="btnGeneric <c:out value="${type}" />" value="<c:out value="${label}" />"/>
    </c:when>
    <c:otherwise>
        <a href="<c:out value="${link}" escapeXml="false" />" class="btnGeneric <c:out value="${type}" />">
            <c:out value="${label}"/>
        </a>
    </c:otherwise>
</c:choose>