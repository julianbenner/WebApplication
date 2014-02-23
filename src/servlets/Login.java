package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.*;

import java.io.IOException;

/**
 * Created by Julian on 09/02/14.
 */
public class Login extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		User userObj = VerifyLogin.verifyLogin(name, password);

		if(userObj == null) {

		} else {
			request.getSession().setAttribute("user", userObj);
		}
		request.setAttribute("referer", request.getHeader("referer"));
		request.setAttribute("userObj", userObj);
		RequestDispatcher view = request.getRequestDispatcher("login_2.jsp");
		view.forward(request, response);
	}
}
