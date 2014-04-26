<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="Detailed book view" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@attribute name="book" required="true" type="main.Book" %>
<%@attribute name="user" required="true" type="main.User" %>
<%@attribute name="showTitle" required="false" type="java.lang.Boolean" %>

<t:bookdetail book="${book}" showTitle="${showTitle}"/>
<div id="buttonsFoot">
    <c:if test="${user != null}">
        <t:button label="Lend" link="lend.do?id=${book.id}" type="btnSubmit"/>
        <c:if test="${user.group == 1 || user.group == 2}">
            <t:button label="Edit" link="changebook.do?id=${book.id}"/>
            <t:button label="Delete" link="deletebook.do?id=${book.id}" type="btnNegative"/>
        </c:if>
    </c:if>
</div>