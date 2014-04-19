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
    $("#authors").append("<span>" + name + '<input type="hidden" name="author" value="' + id + '"/> <a href="#" onclick="$(this).parent().remove();return false;">Remove</a></span><br />');
}