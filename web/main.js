/**
 * Created by Julian on 06/03/14.
 */
function searchAuthors() {
    query = $("#author_search_input").val();
    $.post("search_authors.do", { query: query })
        .done(function (data) {
            $("#author_search").html(data);
        });
}

function addAuthor(id, name) {
    $("#authors").append('<span><a href="#" onclick="$(this).parent().remove();return false;" class="btnGeneric btnSmall btnNegative">Del</a> ' + name + '<input type="hidden" name="author" value="' + id + '"/><br /></span>');
}