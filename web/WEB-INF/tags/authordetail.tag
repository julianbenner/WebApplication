<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="Detailed author view" pageEncoding="UTF-8" %>
<%@attribute name="author" required="true" type="main.Author" %>

<div class="logicalGroup">
    <span class="title"><c:out value="${author.surname}"/>, <c:out value="${author.firstname}"/></span>
</div>