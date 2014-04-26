package servlets;

import main.Book;
import main.SearchResult;
import main.Status;
import main.User;
import servlets.models.VerifyLogin;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Browse extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User userObj = VerifyLogin.getUser(request);
		RequestDispatcher view;
		String action = request.getParameter("action");
		Status status = new Status();
		if (action == null)
			action = "";
		String title = request.getParameter("title");
		//String authors = request.getParameterValues("author");
		String surname = request.getParameter("surname");
		String firstname = request.getParameter("firstname");
		String isbn = request.getParameter("isbn");
		boolean ajax = request.getParameter("ajax") != null && request.getParameter("ajax").equals("1");
		int page;
		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {
			page = 1;
		}

		switch (action) {
			case "authors":
				view = request.getRequestDispatcher("add_book.jsp");
				break;
			case "books":
			default:
				SearchResult searchResult;
				ArrayList<Book> books;
				try {
					searchResult = servlets.models.Book.search(title, surname, firstname, page);
				} catch (SQLException e) {
					searchResult = new SearchResult();
					e.printStackTrace();
				}
				request.setAttribute("searchResult", searchResult);
				request.setAttribute("user", userObj);
				request.setAttribute("titleSearch", title);
				request.setAttribute("surname", surname);
				request.setAttribute("firstname", firstname);
				request.setAttribute("isbn", isbn);
				request.setAttribute("showTitle", false);

				if (ajax) {
					view = request.getRequestDispatcher("browse_books_ajax.jsp");
				} else {
					view = request.getRequestDispatcher("browse_books.jsp");
				}
		}
		request.setAttribute("status", status);
		view.forward(request, response);
	}
}
