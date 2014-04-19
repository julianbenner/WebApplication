package servlets;

import main.Book;
import main.Status;
import main.StatusType;
import main.User;
import servlets.models.Fetch;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowBook extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User userObj = (User) request.getSession().getAttribute("user");
		Status status = new Status();
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("book", Fetch.getBook(id));
		} catch (Exception e) {
			request.setAttribute("book", new Book());
			status.setStatusType(StatusType.FAIL);
			status.setStatus("No or wrong book ID!");
		}
		request.setAttribute("user", userObj);
		request.setAttribute("status", status);
		RequestDispatcher view;

		view = request.getRequestDispatcher("book.jsp");

		view.forward(request, response);
	}
}
