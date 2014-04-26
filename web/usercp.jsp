<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="userObj" class="main.User" scope="request"/>

<t:genericpage>
    <jsp:attribute name="title">
      User Control Panel
    </jsp:attribute>

    <jsp:body>
        <form method="POST" action="usercp.do">
            <div class="logicalGroup">
                <span class="emphasis">Static information</span><br/>
                <span class="emphasis">User name</span><br/>
                <c:out value="${userObj.getName()}"/>
            </div>
            <div id="password" class="logicalGroup">
                <span class="emphasis">Change password</span><br/>
                <input type="password"
                       class="inputGeneric<c:if test="${not empty result}"><c:if test="${result.isOldPasswordWrong()}"> inputWrong</c:if></c:if>"
                       placeholder="Old password" name="oldPw"/><br/>
                <input type="password"
                       class="inputGeneric<c:if test="${not empty result}"><c:if test="${result.isNewPasswordsDontMatch()}"> inputWrong</c:if></c:if>"
                       placeholder="New password" name="newPw"/><br/>
                <input type="password"
                       class="inputGeneric<c:if test="${not empty result}"><c:if test="${result.isNewPasswordsDontMatch()}"> inputWrong</c:if></c:if>"
                       placeholder="New password (confirm)" name="newPw2"/><br/>
            </div>
            <div id="buttonsFoot"><t:button label="Save" link="submit" type="btnSubmit"></t:button></div>
        </form>
    </jsp:body>
</t:genericpage>