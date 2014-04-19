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
import java.net.URL;

public class Login extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		User userObj = VerifyLogin.verifyLogin(name, password);
		Status status = new Status();

		if (userObj == null) {
			status.setStatus("Sorry, username or password were wrong.");
			status.setStatusType(StatusType.FAIL);
		} else {
			request.getSession().setAttribute("user", userObj);
			status.setStatus("Login successful as " + name);
			status.setStatusType(StatusType.SUCCESS);
		}

		request.setAttribute("status", status);
		String referer = new URL(request.getHeader("referer")).getFile();
	/*	String[] splitReferer = referer.split("/");
		String filename = splitReferer[splitReferer.length - 1];
		if (splitReferer.length == 3) filename = "index.jsp";*/
		RequestDispatcher view = request.getRequestDispatcher("login.jsp");
		request.setAttribute("referer1", referer);
		view.forward(request, response);
	}
}
