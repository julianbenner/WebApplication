package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Julian on 09/02/14.
 */
public class Register extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		int id = NewUser.newUser(name, password);

		request.setAttribute("id", id);
		RequestDispatcher view = request.getRequestDispatcher("register_2.jsp");
		view.forward(request, response);
	}
}
