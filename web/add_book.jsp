<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="emptyBook" class="main.Book" scope="request"/>

<t:genericpage>
    <jsp:attribute name="title">
        Add book
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
        <form method="POST" action="addbook.do">
            <t:bookeditable book="${emptyBook}"/>
            <div id="buttonsFoot"><t:button label="Save" link="submit" type="btnSubmit"/></div>
        </form>
    </jsp:body>
</t:genericpage>