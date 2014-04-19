<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="book" class="main.Book" scope="request"/>

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
                $('#author_search_input').keyup(function () {
                    searchAuthors();
                });
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <c:choose>
            <c:when test="${sessionScope.user != null}">
                <div>
                    <form method="POST" action="?action=changebook2&id=<c:out value="${book.id}" />">
                        <div class="logicalGroup">
                            <span class="emphasis">Static information</span><br/>
                            <span class="emphasis">ID</span> <c:out value="${book.id}"/>
                        </div>
                        <div class="logicalGroup">
                            <span class="emphasis">Title</span><br/>
                            <input class="inputGeneric" value="<c:out value="${book.title}" />" name="title"/><br/>
                        </div>
                        <div class="logicalGroup" id="authors">
                            <span class="emphasis">Authors</span><br/>
                            <input class="inputGeneric" id="author_search_input"/><a href="#"
                                                                                     onclick="searchAuthors();return false;">Search</a><br/>

                            <div id="author_search" class="logicalGroup">

                            </div>
                            <c:forEach items="${book.authors}" var="current">
                                <span><c:out value="${current.surname}"/>, <c:out value="${current.firstname}"/><input
                                        type="hidden" name="author" value="<c:out value="${current.id}" />"/> <a
                                        href="#"
                                        onclick="$(this).parent().remove();return false;">Remove</a></span><br/>
                            </c:forEach>
                        </div>
                        <div class="logicalGroup">
                            <span class="emphasis">Details</span><br/>
                            <table>
                                <tr>
                                    <td><input class="inputGeneric" maxlength="13"
                                               value="<c:out value="${book.isbn}" />"
                                               name="isbn" placeholder="ISBN" style="width: 100%"/></td>
                                    <td><input class="inputGeneric" value="<c:out value="${book.publisher}" />"
                                               name="publisher"
                                               placeholder="Publisher" style="width: 100%"/></td>
                                    <td><input type="checkbox" name="available" id="available"
                                               <c:if test="${book.available}">checked</c:if> /><label for="available">Available</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="3"><textarea class="inputGeneric" name="description"
                                                              placeholder="Description (max. 20,000 chars)"
                                                              style="width:420px;height:200px"><c:out
                                            value="${book.description}"/></textarea></td>
                                </tr>
                            </table>
                        </div>
                        <t:button label="Save" link="submit" type="btnSubmit"/>
                        <t:button label="Back" link="book.do?id=${book.id}"/>
                    </form>
                </div>
            </c:when>
            <c:otherwise>
                <p>Please log in first.</p>
            </c:otherwise>
        </c:choose>
    </jsp:body>
</t:genericpage>