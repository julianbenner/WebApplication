<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="Detailed book view" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@attribute name="author" required="true" type="main.Author" %>

<div class="logicalGroup">
    <input class="inputGeneric" name="surname" value="<c:out value="${author.surname}" />"/>, <input
        class="inputGeneric" name="firstname" value="<c:out value="${author.firstname}" />"/>
</div>