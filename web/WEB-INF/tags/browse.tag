<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<%@attribute name="title" fragment="true" %>
<%@attribute name="htmlHeader" fragment="true" %>

<t:genericpage>
    <jsp:attribute name="title">
      User Control Panel
    </jsp:attribute>
    <jsp:attribute name="header">

    </jsp:attribute>
    <jsp:attribute name="footer">

    </jsp:attribute>
    <jsp:body>
        <div class="logicalGroup">
            <form method="GET" action="browse.do?action=books">
                Title: <input class="inputGeneric" name="title" value="<c:out value="${titleSearch}" />" /><br />
                Surname: <input class="inputGeneric" name="surname" value="<c:out value="${surname}" />" ><br />
                First name: <input class="inputGeneric" name="firstname" value="<c:out value="${firstname}" />" /><br />
                <input type="submit" class="btnGeneric" value="Save" />
            </form>
        </div>
        <jsp:doBody/>
    </jsp:body>
</t:genericpage>