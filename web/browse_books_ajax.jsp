<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="books" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="showTitle" class="java.lang.Boolean" scope="request"/>

<div id="results">
    <t:results/>
</div>