<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="book" class="main.Book" scope="request"/>

<t:genericpage>
    <jsp:attribute name="title">
        Change book
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
        <form method="POST" action="?id=<c:out value="${book.id}" />">
            <div class="logicalGroup">
                <span class="emphasis">Static information</span><br/>
                <span class="emphasis">ID</span> <c:out value="${book.id}"/>
            </div>
            <t:bookeditable book="${book}"/>
            <div id="buttonsFoot"><t:button label="Save" link="submit" type="btnSubmit"/>
                <t:button label="Back" link="book.do?id=${book.id}"/></div>
        </form>
    </jsp:body>
</t:genericpage>