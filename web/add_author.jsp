<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="emptyAuthor" class="main.Author" scope="request"/>

<t:genericpage>
    <jsp:attribute name="title">
        Add author
    </jsp:attribute>

    <jsp:body>
        <form method="POST" action="addauthor.do">
            <t:authoreditable author="${emptyAuthor}"/>
            <div id="buttonsFoot"><t:button label="Save" link="submit" type="btnSubmit"/></div>
        </form>
    </jsp:body>
</t:genericpage>