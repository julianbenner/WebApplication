<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
      <h1>Welcome</h1>
    </jsp:attribute>
    <jsp:attribute name="footer">
      <p id="copyright"></p>
    </jsp:attribute>
    <jsp:body>
        You were registered as user <c:out value="${id}" />.<br /><br />
        <a href="index.jsp">Return</a>
    </jsp:body>
</t:genericpage>