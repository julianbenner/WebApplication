<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="searchResult" class="main.SearchResult" scope="request"/>
<jsp:useBean id="user" class="main.User" scope="request"/>
<jsp:useBean id="showTitle" class="java.lang.Boolean" scope="request"/>

<c:forEach items="${searchResult.bookArrayList}" var="book">
    <div class="logicalGroup">
        <span class="author">
            <c:forEach items="${book.authors}" var="current">
                <a href="author.do?id=<c:out value="${current.id}" />"><c:out value="${current.surname}"/>, <c:out
                        value="${current.firstname}"/></a>
            </c:forEach>
        </span><br/>
        <span class="title"><a href="book.do?id=<c:out value="${book.id}" />"><c:out value="${book.title}"/></a></span>

        <t:button label="More" link="#" type="accordionButton"/>
        <div style="display: none;" id="<c:out value="${book.id}" />">
        <t:book user="${user}" book="${book}" showTitle="${showTitle}"/>
        </div>
    </div>

</c:forEach>
<div id="buttonsFoot">
    <c:forEach var="i" begin="1" end="${searchResult.pages}">
        <c:choose>
            <c:when test="${i == searchResult.currentPage}">
                <t:button label="${i}" onclick="searchBooks(${i})" link="#" type="btnSubmit"/>
            </c:when>
            <c:otherwise>
                <t:button label="${i}" onclick="searchBooks(${i})" link="#"/>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</div>