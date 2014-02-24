package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.*;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Julian on 09/02/14.
 */
public class Login extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		User userObj = VerifyLogin.verifyLogin(name, password);
		String status = "";

		if(userObj == null) {

		} else {
			request.getSession().setAttribute("user", userObj);
		}

		if(userObj != null)
			status = "Login successful as " + name;
		else
			status = "Sorry, username or password were wrong.";

		request.setAttribute("status", status);
		String referer = new URL(request.getHeader("referer")).getFile();
		if(referer.contains("/")) referer = "index.jsp";
		request.getRequestDispatcher(referer).forward(request, response);
		//RequestDispatcher view = request.getRequestDispatcher(referer);
		//view.forward(request, response);
	}
}
