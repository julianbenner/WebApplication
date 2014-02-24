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
		String status = "";

		if(id==-1)
			status = "Registration failed";
		else
			status = "User " + name + " registered as ID " + id;

		request.setAttribute("status", status);
		RequestDispatcher view = request.getRequestDispatcher("index.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
		view.forward(request, response);
	}
}
