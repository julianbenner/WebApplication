<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="author" class="main.Author" scope="request"/>

<t:genericpage>
    <jsp:attribute name="title">
      User Control Panel
    </jsp:attribute>
    <jsp:attribute name="header">
        > <a href="<c:url value="/admincp.do"/>">Control panel</a>
    </jsp:attribute>
    <jsp:attribute name="footer">

    </jsp:attribute>
    <jsp:body>
        <c:choose>
            <c:when test="${sessionScope.user != null}">
                <div>
                    <form method="POST" action="?action=changeauthor2&id=<c:out value="${author.id}" />">
                        <div class="logicalGroup">
                            <span class="emphasis">Static information</span><br/>
                            <span class="emphasis">ID</span> <c:out value="${author.id}"/>
                        </div>
                        <div id="password" class="logicalGroup">
                            <span class="emphasis">Name</span><br/>
                            <input class="inputGeneric" value="<c:out value="${author.surname}" />" name="surname"/>,
                            <input class="inputGeneric" value="<c:out value="${author.firstname}" />" name="firstname"/><br/>
                        </div>
                        <input type="submit" class="btnGeneric" value="Save"/>
                    </form>
                </div>
            </c:when>
            <c:otherwise>
                <p>Please log in first.</p>
            </c:otherwise>
        </c:choose>
    </jsp:body>
</t:genericpage>