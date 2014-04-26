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

public class AdminCPController extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User userObj = VerifyLogin.getUser(request);
		RequestDispatcher view;
		Status status = new Status();
		if (userObj != null && userObj.isAdmin()) {
			view = request.getRequestDispatcher("admin_overview.jsp");
		} else {
			status.setStatus("Please log in as admin first.");
			status.setStatusType(StatusType.FAIL);
			request.setAttribute("status", status);
			view = request.getRequestDispatcher("empty.jsp");
		}
		request.setAttribute("status", status);
		view.forward(request, response);
	}
}
