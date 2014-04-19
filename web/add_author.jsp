<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="title">
      Admin Control Panel
    </jsp:attribute>
    <jsp:attribute name="header">
        > <a href="<c:url value="/admincp.do"/>">Control panel</a>
    </jsp:attribute>
    <jsp:attribute name="footer">

    </jsp:attribute>
    <jsp:body>
        <div>
            <form method="POST" action="admincp.do?action=addauthor2">
                <div class="logicalGroup">
                    <span class="emphasis">Surname</span><br/>
                    <input class="inputGeneric" name="surname"/><br/>
                    <span class="emphasis">First name</span><br/>
                    <input class="inputGeneric" name="firstname"/><br/>
                </div>
                <input type="submit" class="btnGeneric" value="Save"/>
            </form>
        </div>
    </jsp:body>
</t:genericpage>