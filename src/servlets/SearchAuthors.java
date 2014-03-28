package servlets;

import main.Author;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Julian on 05/03/14.
 */
public class SearchAuthors extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchQuery = request.getParameter("query");
		List<Author> authors;
		try {
			authors = servlets.models.SearchAuthors.searchAuthors(searchQuery);
		} catch (SQLException e) {
			e.printStackTrace();
			authors = new ArrayList<>();
		}
		RequestDispatcher view = request.getRequestDispatcher("book_list.jsp");
		request.setAttribute("authors", authors);
		view.forward(request, response);
	}
}
