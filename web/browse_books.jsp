<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="books" class="java.util.ArrayList" scope="request"/>

<t:browse>
    <jsp:attribute name="title">
      Browse
    </jsp:attribute>

    <jsp:body>
        <div id="results_ajax">
            <div id="results">
                <t:results/>
            </div>
        </div>
    </jsp:body>
</t:browse>