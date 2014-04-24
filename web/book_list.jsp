<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach items="${authors}" var="current">
    <t:button label="Add" link="#" type="btnSmall btnPositive"
              onclick="addAuthor('${current.id}','${current.surname}, ${current.firstname}');return false;"/>
    <c:out value="${current.surname}"/>, <c:out value="${current.firstname}"/>
    <br/>
</c:forEach>