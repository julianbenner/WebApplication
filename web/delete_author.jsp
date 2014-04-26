<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="user" class="main.User" scope="request"/>
<jsp:useBean id="author" class="main.Author" scope="request"/>
<jsp:useBean id="token" class="java.lang.String" scope="request"/>

<t:genericpage>
    <jsp:attribute name="title">
        Delete
    </jsp:attribute>

    <jsp:body>
        <t:question
                message="You're about to delete the following item. This process can't be undone. Are you sure?">
            <t:button label="Yes, delete" link="?id=${author.id}&token=${token}" type="btnNegative"/>
            <t:button label="No, keep" link="author.do?id=${author.id}"/>
        </t:question>
        <br/>
        <t:authordetail author="${author}"/>
    </jsp:body>
</t:genericpage>