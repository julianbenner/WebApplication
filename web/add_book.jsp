<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="title">
      Admin Control Panel
    </jsp:attribute>
    <jsp:attribute name="header">

    </jsp:attribute>
    <jsp:attribute name="footer">

    </jsp:attribute>
    <jsp:body>
        <div>
            <form method="POST" action="admincp.do?action=addbook2">
                <div class="logicalGroup">
                    <span class="emphasis">Title</span><br />
                    <input class="inputGeneric" name="title" /><br />
                </div>
                <div class="logicalGroup" id="authors">
                    <span class="emphasis">Authors</span><br />
                    <input class="inputGeneric" /><br />
                    <input type="hidden" /><br />
                    <div id="author_search">

                    </div>
                </div>
                <div class="logicalGroup">
                    <span class="emphasis">Addresses</span><br />
                    <c:forEach items="${addresses}" var="current">
                        <c:out value="${current.surname}" />, <c:out value="${current.firstname}" /><br />
                        <c:out value="${current.address1}" /><br />
                        <c:out value="${current.address2}" /><br />
                        <c:out value="${current.address3}" /><br />
                        <c:out value="${current.town}" /> <c:out value="${current.postalCode}" /><br />
                    </c:forEach>
                </div>
                <input type="submit" class="btnGeneric" value="Save" />
            </form>
        </div>
    </jsp:body>
</t:genericpage>