<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="book" class="main.Book" scope="request"/>

<t:genericpage>
    <jsp:attribute name="title">
      User Control Panel
    </jsp:attribute>
    <jsp:attribute name="header">
        > <a href="<c:url value="/admincp.do"/>">Control panel</a>
    </jsp:attribute>
    <jsp:attribute name="footer">

    </jsp:attribute>
    <jsp:attribute name="htmlHeader">
        <script type="text/javascript">
            $(document).ready(function () {
                $('#author_search_input').keyup(function () {
                    searchAuthors();
                });
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <c:choose>
            <c:when test="${sessionScope.user != null}">
                <div>
                    <form method="POST" action="?action=changebook2&id=<c:out value="${book.id}" />">
                        <div class="logicalGroup">
                            <span class="emphasis">Static information</span><br/>
                            <span class="emphasis">ID</span> <c:out value="${book.id}"/>
                        </div>
                        <t:bookeditable book="${book}"/>
                        <t:button label="Save" link="submit" type="btnSubmit"/>
                        <t:button label="Back" link="book.do?id=${book.id}"/>
                    </form>
                </div>
            </c:when>
            <c:otherwise>
                <p>Please log in first.</p>
            </c:otherwise>
        </c:choose>
    </jsp:body>
</t:genericpage>