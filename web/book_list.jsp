<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach items="${authors}" var="current">
    <a href="#" onclick="addAuthor(<c:out value="${current.id}"/>, '<c:out value="${current.surname}"/>, <c:out
            value="${current.firstname}"/>');return false;"><c:out value="${current.surname}"/>, <c:out
            value="${current.firstname}"/></a><br/>
</c:forEach>