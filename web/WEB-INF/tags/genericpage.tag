<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<%@attribute name="title" fragment="true" %>
<%@attribute name="htmlHeader" fragment="true" %>
<html>
<head>
    <title><jsp:invoke fragment="title"/></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/main.css"/>" />
    <jsp:invoke fragment="htmlHeader" />
</head>
<body>
<div id="pageheader">
    <div id="header">
        <div id="heading"><a href="<c:url value="/index.jsp"/>">WebApplication</a><br /><span id="spanSubNav"><a href="<c:url value="/index.jsp"/>">Index</a> <jsp:invoke fragment="header"/></span></div>
        <div id="containerLogin">
            <c:choose>
                <c:when test="${sessionScope.user != null}">
                    <span id="spanLogin">Logged in as <c:out value="${sessionScope.user.getName()}" /><br /><a href="logout.do">Logout</a></span>
                </c:when>
                <c:otherwise>
                    <form method="POST" action="login.do">
                        <input name="name" class="inputLogin" style="position: absolute" placeholder="Username" />
                        <input name="password" type="password" class="inputLogin" id="inputPw" placeholder="Password" />
                        <input type="submit" id="btnLogin" value="Login" />
                    </form>
                    <span id="spanRegNot"><a href="register.jsp">not registered yet?</a></span>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
<div id="body">
    <jsp:doBody/>
</div>
<div id="pagefooter">
    <jsp:invoke fragment="footer"/>
</div>
</body>
</html>