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
import java.sql.SQLException;

public class AddAuthorController extends HttpServlet {
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

		String description = request.getParameter("description");
		String surname = request.getParameter("surname");
		String firstname = request.getParameter("firstname");

		if (userObj != null && userObj.isAdmin()) {
			if (surname != null) {
				try {
					servlets.models.Author.add(surname, firstname);
					status.setStatus("Author successfully added!");
					status.setStatusType(StatusType.SUCCESS);
				} catch (SQLException e) {
					e.printStackTrace();
					status.setStatus("Author could not be added!");
					status.setStatusType(StatusType.FAIL);
				}
				view = request.getRequestDispatcher("empty.jsp");
			} else {
				request.setAttribute("emptyAuthor", new main.Author(true));
				view = request.getRequestDispatcher("add_author.jsp");
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
