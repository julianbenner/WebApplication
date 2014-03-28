<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="title">
      Admin Control Panel
    </jsp:attribute>
    <jsp:attribute name="header">
        > <a href="<c:url value="/admincp.do"/>">Control panel</a>
    </jsp:attribute>
    <jsp:attribute name="footer">

    </jsp:attribute>
    <jsp:attribute name="htmlHeader">
        <script type="text/javascript">
            $(document).ready(function () {
            $('#author_search_input').keyup(function () { searchAuthors(); });
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <div>
            <form method="POST" action="admincp.do?action=addbook2">
                <div class="logicalGroup">
                    <span class="emphasis">Title</span><br />
                    <input class="inputGeneric" name="title" /><br />
                </div>
                <div class="logicalGroup" id="authors">
                    <span class="emphasis">Authors</span><br />
                    <input class="inputGeneric" id="author_search_input" /><a href="#" onclick="searchAuthors();return false;">Search</a><br />
                    <div id="author_search" class="logicalGroup">

                    </div>
                </div>
                <input type="submit" class="btnGeneric" value="Save" />
            </form>
        </div>
    </jsp:body>
</t:genericpage>