package servlets;

import main.*;
import servlets.models.Delete;
import servlets.models.Fetch;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteBook extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User userObj = (User) request.getSession().getAttribute("user");
		Status status = new Status();
		RequestDispatcher view;
		int id = -1;
		if (userObj != null && userObj.isAdmin()) {
			try {
				id = Integer.parseInt(request.getParameter("id"));
				request.setAttribute("book", Fetch.getBook(id));
			} catch (Exception e) {
				request.setAttribute("book", new Book());
				status.setStatus("No or wrong book ID!");
				status.setStatusType(StatusType.FAIL);
			}
			String token = request.getParameter("token"); // get token from GET/POST
			if (token == null || !token.equals(Token.getToken(request.getSession()))) {
				token = Token.getToken(request.getSession());
				request.setAttribute("token", token);
				view = request.getRequestDispatcher("delete_book.jsp");
			} else { // does token correspond to user?
				if (Delete.deleteBook(id)) // if so, try to delete book
					view = request.getRequestDispatcher("delete_book_success.jsp");
				else
					view = request.getRequestDispatcher("delete_book_failed.jsp");
			}
		} else {
			status.setStatus("You need to be logged in as admin to do this.");
			status.setStatusType(StatusType.FAIL);
			view = request.getRequestDispatcher("index.jsp");
		}
		request.setAttribute("user", userObj);
		request.setAttribute("status", status);
		view.forward(request, response);
	}
}
