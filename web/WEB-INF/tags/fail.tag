<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@tag description="Negative notification" pageEncoding="UTF-8" %>
<%@attribute name="message" required="true" type="java.lang.String" %>

<t:notification message="${message}" type="fail" icon=":(">
    <jsp:doBody/>
</t:notification>