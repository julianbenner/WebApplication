<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="title">
      Register
    </jsp:attribute>
    <jsp:attribute name="header">
      > <a href="<c:url value="/register.jsp"/>">Register</a>
    </jsp:attribute>
    <jsp:attribute name="footer">
      <p id="copyright"></p>
    </jsp:attribute>
    <jsp:body>
        <form method="POST" action="register.do">
            <div class="input"><input name="name" /><br />User name</div>
            <div class="input"><input name="password" type="password" /><br />Password</div>
            <input type="submit" />
        </form>
    </jsp:body>
</t:genericpage>