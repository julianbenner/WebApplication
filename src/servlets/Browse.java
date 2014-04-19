package servlets;

import main.Author;
import main.Book;
import main.Status;
import main.User;
import servlets.models.Search;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Browse extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User userObj = (User) request.getSession().getAttribute("user");
		RequestDispatcher view;
		String action = request.getParameter("action");
		Status status = new Status();
		if (action == null)
			action = "";
		String title = request.getParameter("title");
		//String authors = request.getParameterValues("author");
		String surname = request.getParameter("surname");
		String firstname = request.getParameter("firstname");
		Author author;
		switch (action) {
			case "authors":
				view = request.getRequestDispatcher("add_book.jsp");
				break;
			case "books":
			default:
				List<Book> books;
				try {
					books = Search.searchBook(title, surname, firstname);
				} catch (SQLException e) {
					books = new ArrayList<>();
					e.printStackTrace();
				}
				request.setAttribute("books", books);
				request.setAttribute("titleSearch", title);
				request.setAttribute("surname", surname);
				request.setAttribute("firstname", firstname);
				view = request.getRequestDispatcher("browse_books.jsp");
		}
		request.setAttribute("status", status);
		view.forward(request, response);
	}
}
