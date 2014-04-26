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

public class LendingsController extends HttpServlet {
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

		if (userObj != null) {
			try {
				request.setAttribute("lendings", servlets.models.Lending.getUserLendings(userObj.getId()));
				view = request.getRequestDispatcher("lendings.jsp");
			} catch (Exception e) {
				status.setStatusType(StatusType.FAIL);
				status.setStatus("No or wrong book ID!");
				view = request.getRequestDispatcher("empty.jsp");

			}
			request.setAttribute("user", userObj);
		} else {
			status.setStatus("You need to be logged in to do this.");
			status.setStatusType(StatusType.FAIL);
			view = request.getRequestDispatcher("empty.jsp");
		}

		request.setAttribute("status", status);
		view.forward(request, response);
	}
}
