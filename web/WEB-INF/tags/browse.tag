<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<%@attribute name="title" fragment="true" %>
<%@attribute name="htmlHeader" fragment="true" %>
<jsp:useBean id="titleSearch" class="java.lang.String" scope="request"/>
<jsp:useBean id="isbn" class="java.lang.String" scope="request"/>
<jsp:useBean id="firstname" class="java.lang.String" scope="request"/>
<jsp:useBean id="surname" class="java.lang.String" scope="request"/>

<t:genericpage>
    <jsp:attribute name="title">
      User Contdol Panel
    </jsp:attribute>
    <jsp:attribute name="header">

    </jsp:attribute>
    <jsp:attribute name="footer">

</jsp:attribute>
    <jsp:body>
        <div class="logicalGroup">
            <form method="GET" autocomplete="off" action="browse.do?action=books">
            <table>
                    <tr>
                        <td><input class="inputGeneric" placeholder="Title" id="title" name="title"
                                   value="<c:out value="${titleSearch}" />"/></td>
                        <td><input maxlength="13" pattern="97[89][0-9]{10}" class="inputGeneric" placeholder="ISBN-13"
                                   name="isbn" value="<c:out value="${isbn}" />"/></td>
                    </tr>
                    <tr>
                        <td><input class="inputGeneric" placeholder="Author first name" name="firstname"
                                   value="<c:out value="${firstname}" />"/></td>
                        <td><input class="inputGeneric" placeholder="Author surname" name="surname"
                                   value="<c:out value="${surname}" />"></td>
                    </tr>
                    <tr>
                        <td>
                            <t:button label="Search" link="submit" type="btnSubmit"/>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <jsp:doBody/>
    </jsp:body>
</t:genericpage>