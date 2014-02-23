<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="htmlHeader">
        <meta http-equiv="refresh" content="4; url=<c:out value="${referer}" />" />
    </jsp:attribute>
    <jsp:attribute name="header">
      > Login
    </jsp:attribute>
    <jsp:attribute name="footer">
      <p id="copyright"></p>
    </jsp:attribute>
    <jsp:body>
        <c:choose>
            <c:when test="${userObj != null}">
                Login successful as <c:out value="${userObj.getName()}" />.
            </c:when>
            <c:otherwise>
                Sorry, username or password were wrong.
            </c:otherwise>
        </c:choose><br />
        Redirecting you back to your last page... (If you are not being redirected, click <a href="${referer}">here</a>.)
    </jsp:body>
</t:genericpage>