<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="author" class="main.Author" scope="request"/>

<t:genericpage>
    <jsp:attribute name="title">
        Edit author
    </jsp:attribute>

    <jsp:body>
        <form method="POST" action="?id=<c:out value="${author.id}" />">
            <div class="logicalGroup">
                <span class="emphasis">Static information</span><br/>
                <span class="emphasis">ID</span> <c:out value="${author.id}"/>
            </div>
            <t:authoreditable author="${author}"/>
            <div id="buttonsFoot"><t:button label="Save" link="submit" type="btnSubmit"/>
                <t:button label="Back" link="author.do?id=${author.id}"/></div>
        </form>
    </jsp:body>
</t:genericpage>