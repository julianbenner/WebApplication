package servlets;

import main.Status;
import main.StatusType;
import main.User;
import servlets.models.Book;
import servlets.models.VerifyLogin;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddBookController extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User userObj = VerifyLogin.getUser(request);
		Status status = new Status();
		RequestDispatcher view;

		String title = request.getParameter("title");
		String[] authors = request.getParameterValues("author");
		String isbn = request.getParameter("isbn");
		String publisher = request.getParameter("publisher");
		boolean available = request.getParameter("available") != null && request.getParameter("available").equals("on");
		String description = request.getParameter("description");

		if (userObj != null && userObj.isAdmin()) {
			if (title != null) {
				try {
					ArrayList<Integer> authorsInt = new ArrayList<>();
					for (String authorIterator : authors) {
						try {
							authorsInt.add(Integer.parseInt(authorIterator));
						} catch (NumberFormatException e) {
							e.printStackTrace();
						}
					}
					Book.add(title, authorsInt, isbn, publisher, available, description);
					status.setStatus("Book successfully added!");
					status.setStatusType(StatusType.SUCCESS);
				} catch (SQLException e) {
					e.printStackTrace();
					status.setStatus("Book could not be added!");
					status.setStatusType(StatusType.FAIL);
				}
				view = request.getRequestDispatcher("empty.jsp");
			} else {
				request.setAttribute("emptyBook", new main.Book(true));
				view = request.getRequestDispatcher("add_book.jsp");
			}
		} else {
			status.setStatus("You need to be logged in as admin to do this.");
			status.setStatusType(StatusType.FAIL);
			view = request.getRequestDispatcher("empty.jsp");
		}

		request.setAttribute("user", userObj);
		request.setAttribute("status", status);
		view.forward(request, response);
	}
}
