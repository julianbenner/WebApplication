<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="title">
        Unlend
    </jsp:attribute>
    <jsp:attribute name="footer">
      <p id="copyright"></p>
    </jsp:attribute>
    <jsp:attribute name="notificationBody">
            <t:button label="Browse" link="browse.do" type="btnSubmit"/>
    </jsp:attribute>
    <jsp:body>
    </jsp:body>
</t:genericpage>