/**
 * Created by Julian on 06/03/14.
 */
function searchBookLogic() {
    delay(function () {
        if (!alreadyPressedEnter)
            searchBooks();
        else
            alreadyPressedEnter = false;
    }, 200);
}

var delay = (function () {
    var timer = 0;
    return function (callback, ms) {
        clearTimeout(timer);
        timer = setTimeout(callback, ms);
    };
})();

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

function searchBooks() {
    searchBooks(1);
}

function searchBooks(page) {
    title = $("#title").val();
    isbn = $("#isbn").val();
    surname = $("#surname").val();
    firstname = $("#firstname").val();
    $.post("browse.do?ajax=1", { title: title, isbn: isbn, surname: surname, firstname: firstname, page: page })
        .done(function (data) {
            $("#results_ajax").fadeOut(125, function () {
                $("#results_ajax").html(data);
                $("#results_ajax").fadeIn(125, function () {
                    accordionfy();
                });
            });
        });
}

function accordionfy() {
    $(function () {
        $("#results").accordion({ active: false, collapsible: true, heightStyle: "content", header: ".accordionButton", animate: 133}/*{
         active: false,
         activate: function(event, ui){
         var clicked = $(this).find('.ui-state-active').next().attr('id');
         $('#'+clicked).load('book.do?ajax=1&id='+clicked);
         },
         heightStyle: "content"
         }*/);
    });
}