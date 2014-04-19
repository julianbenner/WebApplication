<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@tag description="Positive notification" pageEncoding="UTF-8" %>
<%@attribute name="message" required="true" type="java.lang.String" %>

<t:notification message="${message}" type="success" icon=":)">
    <jsp:doBody/>
</t:notification>