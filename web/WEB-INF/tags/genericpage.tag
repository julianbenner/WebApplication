<%@tag description="Overall page template" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
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
    <script src="jquery-ui-1.10.4.custom.min.js" type="text/javascript"></script>
    <script src="main.js" type="text/javascript"></script>
    <meta charset="utf-8">
    <jsp:invoke fragment="htmlHeader"/>
</head>
<body>
<div id="page">
    <div id="pageheader">
        <div id="header">
            <div id="headLogoContainer">
                <a href="<c:url value="/index.jsp"/>" id="headLogoLink">DogeLibrary</a>
            </div>
            <div id="headButtons">
                <a href="browse.do" class="headButton">Browse</a>
                <c:if test="${sessionScope.user != null}">
                    <a href="lendings.do" class="headButton">Lendings</a>
                    <c:if test="${sessionScope.user.isAdmin()}">
                        <a href="admincp.do" class="headButton">Control panel</a>
                    </c:if>
                </c:if>
            </div>
            <div id="headLogin">
                <c:choose>
                    <c:when test="${sessionScope.user != null}">
                        <a href="usercp.do" class="headButton"><c:out
                                value="${sessionScope.user.getName()}"/></a>
                        <a href="logout.do" class="headButton">Logout</a>
                    </c:when>
                    <c:otherwise>
                        <a href="login.do" class="headButton">Login</a>
                        <a href="register.do" class="headButton">Register</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
    <div id="bodyWrapper">
        <div id="body">
            <span class="title"><jsp:invoke fragment="title"/></span>
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
            <c:when test="${status.statusType == 'QUESTION'}">
                <t:question message="${status.status}">
                    <jsp:invoke fragment="notificationBody"/>
                </t:question>
            </c:when>
            <c:otherwise></c:otherwise>
        </c:choose>
        <jsp:doBody/>
        </div>
    </div>
</div>
</body>
</html>