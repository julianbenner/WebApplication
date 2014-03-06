<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="title">
      User Control Panel
    </jsp:attribute>
    <jsp:attribute name="header">

    </jsp:attribute>
    <jsp:attribute name="footer">

    </jsp:attribute>
    <jsp:body>
        <c:choose>
            <c:when test="${sessionScope.user != null}">
                <div>
                    <form method="POST" action="usercp.do">
                        <div class="logicalGroup">
                            <span class="emphasis">Static information</span><br />
                            <span class="emphasis">User name</span><br />
                            <c:out value="${userObj.getName()}" />
                        </div>
                        <div id="password" class="logicalGroup">
                            <span class="emphasis">Change password</span><br />
                            <input type="password" class="inputGeneric<c:if test="${not empty result}"><c:if test="${result.isOldPasswordWrong()}"> inputWrong</c:if></c:if>" placeholder="Old password" name="oldPw" /><br />
                            <input type="password" class="inputGeneric<c:if test="${not empty result}"><c:if test="${result.isNewPasswordsDontMatch()}"> inputWrong</c:if></c:if>" placeholder="New password" name="newPw" /><br />
                            <input type="password" class="inputGeneric<c:if test="${not empty result}"><c:if test="${result.isNewPasswordsDontMatch()}"> inputWrong</c:if></c:if>" placeholder="New password (confirm)" name="newPw2" /><br />
                        </div>
                        <div class="logicalGroup">
                            <span class="emphasis">Addresses</span><br />
                            <c:forEach items="${addresses}" var="current">
                                <c:out value="${current.surname}" />, <c:out value="${current.firstname}" /><br />
                                <c:out value="${current.address1}" /><br />
                                <c:out value="${current.address2}" /><br />
                                <c:out value="${current.address3}" /><br />
                                <c:out value="${current.town}" /> <c:out value="${current.postalCode}" /><br />
                            </c:forEach>g
                        </div>
                        <input type="submit" class="btnGeneric" value="Save" />
                    </form>
                </div>
            </c:when>
            <c:otherwise>
                <p>Please log in first.</p>
            </c:otherwise>
        </c:choose>
    </jsp:body>
</t:genericpage>