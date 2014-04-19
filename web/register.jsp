<%@page contentType="text/html" pageEncoding="UTF-8" %>
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
        <div class="logicalGroup">
            <form method="POST" action="register.do">
                <input class="inputGeneric" placeholder="User name" name="name" type="text" required="required"
                       pattern="^[a-z\d\.]{5,}$"/><br/>
                <input class="inputGeneric" placeholder="Password" name="password" required="required" type="password"/><br/>
                <t:button label="Register" link="submit" type="btnSubmit"/>
        </form>
        </div>
    </jsp:body>
</t:genericpage>