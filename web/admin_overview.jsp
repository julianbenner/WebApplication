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
        <c:choose>
            <c:when test="${sessionScope.user != null}">
                <div>
                    <form method="POST" action="usercp.do">
                        <div class="logicalGroup">
                            <span class="emphasis">Users</span><br/>
                        </div>
                        <div class="logicalGroup">
                            <span class="emphasis">Books</span><br/>
                            <a href="?action=addbook">Add book</a>
                            <a href="?action=changebook">Change book</a>
                        </div>
                        <div class="logicalGroup">
                            <span class="emphasis">Authors</span><br/>
                            <a href="?action=addauthor">Add author</a>
                            <a href="?action=changeauthor">Change author</a>
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