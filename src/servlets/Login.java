package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.*;
import servlets.models.VerifyLogin;

import java.io.IOException;
import java.net.URL;

public class Login extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		User userObj = VerifyLogin.verifyLogin(name, password);
		String status = "";

		if(userObj == null) {
			status = "Sorry, username or password were wrong.";
		} else {
			request.getSession().setAttribute("user", userObj);
			status = "Login successful as " + name;
		}

		request.setAttribute("status", status);
		String referer = new URL(request.getHeader("referer")).getFile();
		if(referer.contains("/")) referer = "index.jsp";
		request.getRequestDispatcher(referer).forward(request, response);
	}
}
