<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="referer1" class="java.lang.String" scope="request"/>

<t:genericpage>
    <jsp:attribute name="title">
        Login
    </jsp:attribute>

    <jsp:body>
        <div class="logicalGroup">
            <form method="POST" action="login.do" style="margin:auto">
                <input name="name" class="inputGeneric" style="position: absolute"
                       placeholder="Username"/><br/>
                <input name="password" type="password" class="inputGeneric" id="inputPw"
                       placeholder="Password"/><br/>
                <input type="checkbox" name="staylogin" id="staylogin"/>
                <label for="staylogin">Stay logged in</label><br/>
                <input type="submit" class="btnGeneric btnSubmit" value="Login"/>
            </form>
        </div>
    </jsp:body>
</t:genericpage>