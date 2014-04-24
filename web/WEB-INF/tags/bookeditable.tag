<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="Detailed book view" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@attribute name="book" required="true" type="main.Book" %>

<div class="logicalGroup">
    <span class="emphasis">Title</span><br/>
    <input class="inputGeneric" value="<c:out value="${book.title}" />" name="title"/><br/>
</div>

<div class="logicalGroup">
    <span class="emphasis">Authors</span><br/>

    <input class="inputGeneric" id="author_search_input" autocomplete="off"/>
    <t:button label="Search" link="#" onclick="searchAuthors();return false;" type="btnSubmit"/>
    <br/>

    <div id="author_search">

    </div>


    <div class="logicalGroup" id="authors">
        <c:forEach items="${book.authors}" var="current">
            <span>
                <a href="#" onclick="$(this).parent().remove();return false;" class="btnGeneric btnSmall btnNegative">Del</a>
                <c:out value="${current.surname}"/>, <c:out value="${current.firstname}"/>
                <input type="hidden" name="author" value="<c:out value="${current.id}" />"/>
                <br/>
            </span>
        </c:forEach>
    </div>
</div>

<div class="logicalGroup">
    <span class="emphasis">Details</span><br/>
    <table>
        <tr>
            <td>
                <input class="inputGeneric" maxlength="13"
                       value="<c:out value="${book.isbn}" />"
                       name="isbn" placeholder="ISBN" style="width: 100%"/>
            </td>
            <td>
                <input class="inputGeneric" value="<c:out value="${book.publisher}" />"
                       name="publisher"
                       placeholder="Publisher" style="width: 100%"/>
            </td>
            <td>
                <input type="checkbox" name="available" id="available"
                       <c:if test="${book.available}">checked</c:if> />
                <label for="available">Available</label>
            </td>
        </tr>
        <tr>
            <td colspan="3">
                <textarea class="inputGeneric" name="description"
                          placeholder="Description (max. 20,000 chars)"
                          style="width:420px; height:200px"><c:out value="${book.description}"/></textarea>
            </td>
        </tr>
    </table>
</div>