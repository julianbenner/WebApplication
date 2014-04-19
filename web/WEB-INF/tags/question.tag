<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@tag description="Question notification" pageEncoding="UTF-8" %>
<%@attribute name="message" required="true" type="java.lang.String" %>

<t:notification message="${message}" type="question" icon="?">
    <jsp:doBody/>
</t:notification>