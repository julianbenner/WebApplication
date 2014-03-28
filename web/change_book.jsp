<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="title">
      User Control Panel
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
        <c:choose>
            <c:when test="${sessionScope.user != null}">
                <div>
                    <form method="POST" action="?action=changebook2&id=<c:out value="${book.id}" />">
                        <div class="logicalGroup">
                            <span class="emphasis">Static information</span><br />
                            <span class="emphasis">ID</span> <c:out value="${book.id}" />
                        </div>
                        <div class="logicalGroup">
                            <span class="emphasis">Title</span><br />
                            <input class="inputGeneric" value="<c:out value="${book.title}" />" name="title" /><br />
                        </div>
                        <div class="logicalGroup" id="authors">
                            <span class="emphasis">Authors</span><br />
                            <input class="inputGeneric" id="author_search_input" /><a href="#" onclick="searchAuthors();return false;">Search</a><br />
                            <div id="author_search" class="logicalGroup">

                            </div>
                            <c:forEach items="${book.authors}" var="current">
                                <span><c:out value="${current.surname}" />, <c:out value="${current.firstname}" /><input type="hidden" name="author" value="<c:out value="${current.id}" />"/> <a href="#" onclick="$(this).parent().remove();return false;">Remove</a></span><br />
                           </c:forEach>
                        </div>
                        <div class="logicalGroup">
                            <span class="emphasis">Details</span><br />
                            <input class="inputGeneric" value="<c:out value="${book.isbn}" />" name="isbn" placeholder="ISBN" /><br />
                            <input class="inputGeneric" value="<c:out value="${book.publisher}" />" name="publisher" placeholder="Publisher" /><br />
                            <textarea class="inputGeneric" name="description" placeholder="Description (max. 20,000 chars)"><c:out value="${book.description}" /></textarea><br />
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