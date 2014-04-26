package servlets;

import main.Status;
import main.StatusType;
import main.User;
import servlets.models.VerifyLogin;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BookController extends HttpServlet {
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
		boolean ajax = request.getParameter("ajax") != null && request.getParameter("ajax").equals("1");

		try {
			int id = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("book", servlets.models.Book.get(id));
			if (ajax) {
				request.setAttribute("showTitle", false);
				view = request.getRequestDispatcher("book_ajax.jsp");
			} else {
				request.setAttribute("showTitle", true);
				view = request.getRequestDispatcher("book.jsp");
			}
		} catch (Exception e) {
			status.setStatusType(StatusType.FAIL);
			status.setStatus("No or wrong book ID!");
			view = request.getRequestDispatcher("empty.jsp");

		}
		request.setAttribute("user", userObj);
		request.setAttribute("status", status);

		view.forward(request, response);
	}
}
