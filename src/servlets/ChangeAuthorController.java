package servlets;

import main.Status;
import main.StatusType;
import main.User;
import servlets.models.Author;
import servlets.models.VerifyLogin;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ChangeAuthorController extends HttpServlet {
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

		main.Author author;
		String idString = request.getParameter("id");
		String surname = request.getParameter("surname");
		String firstname = request.getParameter("firstname");

		if (userObj != null && userObj.isAdmin()) {
			if (surname != null) {
				try {
					author = Author.change(Integer.parseInt(idString), surname, firstname);
					request.setAttribute("author", author);
					status.setStatus("Author successfully changed!");
					status.setStatusType(StatusType.SUCCESS);
					view = request.getRequestDispatcher("change_author.jsp");
				} catch (SQLException e) {
					e.printStackTrace();
					status.setStatus("Author could not be changed!");
					status.setStatusType(StatusType.FAIL);
					view = request.getRequestDispatcher("empty.jsp");
				}
			} else {
				try {
					author = Author.get(Integer.parseInt(idString));
					request.setAttribute("author", author);
					view = request.getRequestDispatcher("change_author.jsp");
				} catch (SQLException | NumberFormatException e) {
					e.printStackTrace();
					status.setStatus("No or wrong author ID!");
					status.setStatusType(StatusType.FAIL);
					view = request.getRequestDispatcher("empty.jsp");
				}
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
