<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="user" class="main.User" scope="request"/>
<jsp:useBean id="author" class="main.Author" scope="request"/>

<t:genericpage>
    <jsp:attribute name="title">
        <c:out value="${author.surname}"/>, <c:out value="${author.firstname}"/>
    </jsp:attribute>
    <jsp:body>
        <t:authordetail author="${author}"/>
        <div id="buttonsFoot">
            <c:if test="${user.group == 1 || user.group == 2}">
                <t:button label="Edit" link="changeauthor.do?id=${author.id}"/>
                <t:button label="Delete" link="deleteauthor.do?id=${author.id}" type="btnNegative"/>
            </c:if>
        </div>
    </jsp:body>
</t:genericpage>