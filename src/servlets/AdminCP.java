package servlets;

import main.*;
import servlets.models.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminCP extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processReguest(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processReguest(request, response);
	}

	public void processReguest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User userObj = (User)request.getSession().getAttribute("user");
		RequestDispatcher view;
		String status = "";
		if(userObj == null) {
			status = "Please log in first.";
			view = request.getRequestDispatcher("index.jsp");
		} else {
			String action = request.getParameter("action");
			if(action == null)
				action = "";
			String title = request.getParameter("title");
			String[] authors = request.getParameterValues("author");
			String isbn = request.getParameter("isbn");
			String publisher = request.getParameter("publisher");
			String description = request.getParameter("description");
			String surname = request.getParameter("surname");
			String firstname = request.getParameter("firstname");
			String idString = request.getParameter("id");
			Author author;
			Book book;
			List<Integer> authorsInt;
			switch (action) {
				case "addbook":
					view = request.getRequestDispatcher("add_book.jsp");
					break;
				case "addbook2":
					authorsInt = new ArrayList<>();
					for(String authorIterator: authors) {
						try {
							authorsInt.add(Integer.parseInt(authorIterator));
						} catch (NumberFormatException e) {
							e.printStackTrace();
						}
					}
					try {
						AddBook.addBook(title, authorsInt);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					view = request.getRequestDispatcher("add_book.jsp");
					break;
				case "addauthor":
					view = request.getRequestDispatcher("add_author.jsp");
					break;
				case "addauthor2":
					try {
						AddAuthor.addAuthor(surname, firstname);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					view = request.getRequestDispatcher("add_author.jsp");
					break;
				case "changeauthor":
					try {
						author = Fetch.getAuthor(Integer.parseInt(idString));
					} catch (SQLException e) {
						author = new Author();
						e.printStackTrace();
					}
					request.setAttribute("author", author);
					view = request.getRequestDispatcher("change_author.jsp");
					break;
				case "changeauthor2":
					author = Change.changeAuthor(Integer.parseInt(idString), surname, firstname);
					request.setAttribute("author", author);
					view = request.getRequestDispatcher("change_author.jsp");
					break;
				case "changebook":
					try {
						book = Fetch.getBook(Integer.parseInt(idString));
					} catch (SQLException e) {
						book = new Book();
						e.printStackTrace();
					}
					request.setAttribute("book", book);
					view = request.getRequestDispatcher("change_book.jsp");
					break;
				case "changebook2":
					authorsInt = new ArrayList<>();
					for(String authorIterator: authors) {
						try {
							authorsInt.add(Integer.parseInt(authorIterator));
						} catch (NumberFormatException e) {
							e.printStackTrace();
						}
					}
					book = Change.changeBook(Integer.parseInt(idString), title, authorsInt, isbn, publisher, description);
					request.setAttribute("book", book);
					view = request.getRequestDispatcher("change_book.jsp");
					break;
				default:
					view = request.getRequestDispatcher("admin_overview.jsp");
			}
		}
		request.setAttribute("status", status);
		view.forward(request, response);
	}
}
