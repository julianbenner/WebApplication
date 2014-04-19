<%@tag description="Overall page template" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<%@attribute name="title" fragment="true" %>
<%@attribute name="htmlHeader" fragment="true" %>
<%@attribute name="notificationBody" fragment="true" %>
<jsp:useBean id="status" class="main.Status" scope="request"/>

<html>
<head>
    <title>Doge &ndash;
        <jsp:invoke fragment="title"/>
    </title>
    <link rel="shortcut icon" href="doge.png"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/main.css"/>"/>
    <!--<link rel="stylesheet" type="text/css" href="<c:url value="/doge.css"/>" /> -->
    <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
    <script src="main.js" type="text/javascript"></script>
    <meta charset="utf-8">
    <jsp:invoke fragment="htmlHeader"/>
</head>
<body>
<div id="pageheader">
    <div id="header">
        <div id="heading">
            <a href="<c:url value="/index.jsp"/>"
               style="display: inline-block;background: url(doge_medium.png); padding-left: 40px; background-repeat: no-repeat">DogeLibrary</a><br/>
            <span id="spanSubNav"><a href="browse.do">Browse</a> | <a href="admincp.do">Control panel</a></span><br/>
            <span id="spanBreadcrumbs"><a href="<c:url value="/index.jsp"/>">Index</a> <jsp:invoke
                    fragment="header"/></span>
        </div>
        <div id="containerLogin">
            <c:choose>
                <c:when test="${sessionScope.user != null}">
                    <span id="spanLogin">Logged in as <a href="<c:url value="/usercp.do"/>"><c:out
                            value="${sessionScope.user.getName()}"/></a><br/><a href="logout.do">Logout</a></span>
                </c:when>
                <c:otherwise>
                    <form method="POST" action="login.do">
                        <input name="name" class="inputGeneric inputLogin" style="position: absolute"
                               placeholder="Username"/>
                        <input name="password" type="password" class="inputGeneric inputLogin" id="inputPw"
                               placeholder="Password"/>
                        <input type="submit" class="btnGeneric" id="btnLogin" value="Login"/>
                    </form>
                    <span id="spanRegNot"><a href="register.jsp">not registered yet?</a></span>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
<div id="body">
    <c:choose>
        <c:when test="${status.statusType == 'FAIL'}">
            <t:fail message="${status.status}">
                <jsp:invoke fragment="notificationBody"/>
            </t:fail>
        </c:when>
        <c:when test="${status.statusType == 'SUCCESS'}">
            <t:success message="${status.status}">
                <jsp:invoke fragment="notificationBody"/>
            </t:success>
        </c:when>
        <c:when test="${status.statusType == 'INFORMATION'}">
            <t:information message="${status.status}">
                <jsp:invoke fragment="notificationBody"/>
            </t:information>
        </c:when>
        <c:otherwise></c:otherwise>
    </c:choose>
    <jsp:doBody/>
</div>
<div id="pagefooter">
    <jsp:invoke fragment="footer"/>
</div>
</body>
</html>