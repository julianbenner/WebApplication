<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="emptyBook" class="main.Book" scope="request"/>

<t:genericpage>
    <jsp:attribute name="title">
      Admin Control Panel
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
        <div>
            <form method="POST" action="admincp.do?action=addbook2">
                <t:bookeditable book="${emptyBook}"/>
                <input type="submit" class="btnGeneric" value="Save"/>
            </form>
        </div>
    </jsp:body>
</t:genericpage>