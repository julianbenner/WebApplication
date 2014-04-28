<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="lendings" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="book" class="java.lang.Integer" scope="request"/>

<t:genericpage>
    <jsp:attribute name="title">
        Lendings
    </jsp:attribute>

    <jsp:body>
        <span class="title">Lendings of book <c:out value="${book}"/></span>
        <c:forEach items="${lendings}" var="lending">
            <div class="logicalGroup">
                <a href="user.do?id=<c:out value="${lending.user.id}" />"><c:out
                        value="${lending.user.name}"/></a>
                | From <c:out value="${lending.date}"/>, for <c:out
                    value="${lending.duration}"/> days | <a
                    href="unlend.do?id=<c:out value="${lending.id}" />">(Unlend)</a>
            </div>
        </c:forEach>
    </jsp:body>
</t:genericpage>