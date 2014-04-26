<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="title">
      Admin Control Panel
    </jsp:attribute>
    <jsp:body>
        <div class="logicalGroup">
            <span class="smallEmphasis">Users</span><br/>
        </div>
        <div class="logicalGroup">
            <span class="smallEmphasis">Books</span><br/>
            <a href="addbook.do">Add book</a>
        </div>
        <div class="logicalGroup">
            <span class="smallEmphasis">Authors</span><br/>
            <a href="addauthor.do">Add author</a>
        </div>
    </jsp:body>
</t:genericpage>