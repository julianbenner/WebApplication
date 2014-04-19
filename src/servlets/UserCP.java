package servlets;

import main.ChangeUserResult;
import main.Status;
import main.StatusType;
import main.User;
import servlets.models.ChangeUser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserCP extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User userObj = (User) request.getSession().getAttribute("user");
		ChangeUserResult result;
		Status status = new Status();
		status.setStatus("Changes were applied. If inputs are red, check and re-submit them.");
		status.setStatusType(StatusType.INFORMATION);
		if (userObj == null) {
			status.setStatus("Please log in first.");
			status.setStatusType(StatusType.FAIL);
			result = null;
		} else {
			request.getSession().setAttribute("user", userObj);
			request.setAttribute("userObj", userObj);

			String oldPw = request.getParameter("oldPw");
			String newPw = request.getParameter("newPw");
			String newPw2 = request.getParameter("newPw2");

			result = ChangeUser.changeUser(userObj.getName(), oldPw, newPw, newPw2);
		}
		request.setAttribute("status", status);
		request.setAttribute("result", result);
		RequestDispatcher view = request.getRequestDispatcher("usercp.jsp");
		view.forward(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User userObj = (User) request.getSession().getAttribute("user");
		String status = "";
		if (userObj == null) {
			status = "Please log in first.";
		} else {
			request.getSession().setAttribute("user", userObj);
			request.setAttribute("userObj", userObj);
		}
		request.setAttribute("status", status);
		RequestDispatcher view = request.getRequestDispatcher("usercp.jsp");
		view.forward(request, response);
	}
}
