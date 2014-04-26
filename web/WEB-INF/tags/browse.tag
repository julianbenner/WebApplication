<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="footer" fragment="true" %>
<%@attribute name="title" fragment="true" %>
<%@attribute name="htmlHeader" fragment="true" %>
<jsp:useBean id="titleSearch" class="java.lang.String" scope="request"/>
<jsp:useBean id="isbn" class="java.lang.String" scope="request"/>
<jsp:useBean id="firstname" class="java.lang.String" scope="request"/>
<jsp:useBean id="surname" class="java.lang.String" scope="request"/>

<t:genericpage>
    <jsp:attribute name="title">
        Browse
    </jsp:attribute>

    <jsp:attribute name="htmlHeader">
        <script type="text/javascript">
            $(document).ready(function () {
                alreadyPressedEnter = false;

                $('#searchForm').keypress(function () {
                    searchBookLogic();
                });

                $('#searchForm').bind('keypress', function (e) {
                    if (e.keyCode == 13) {
                        alreadyPressedEnter = true;
                        searchBooks();
                    } else if (e.keyCode == 8) {
                        searchBookLogic();
                    }
                });
            });

            accordionfy();
        </script>
    </jsp:attribute>

    <jsp:body>
        <div class="logicalGroup">
            <form method="GET" autocomplete="off" action="browse.do?action=books" id="searchForm">
                <table>
                    <tr>
                        <td><input class="inputGeneric" placeholder="Title" id="title" name="title"
                                   value="<c:out value="${titleSearch}" />"/></td>
                        <td><input maxlength="13" pattern="97[89][0-9]{10}" class="inputGeneric" placeholder="ISBN-13"
                                   name="isbn" id="isbn" value="<c:out value="${isbn}" />"/></td>
                    </tr>
                    <tr>
                        <td><input class="inputGeneric" placeholder="Author first name" name="firstname"
                                   id="firstname" value="<c:out value="${firstname}" />"/></td>
                        <td><input class="inputGeneric" placeholder="Author surname" name="surname"
                                   id="surname" value="<c:out value="${surname}" />"></td>
                    </tr>
                </table>
            </form>
        </div>
        <jsp:doBody/>
    </jsp:body>
</t:genericpage>