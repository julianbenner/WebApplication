package servlets;

import main.Status;
import main.StatusType;
import servlets.models.NewUser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Register extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		int id = NewUser.newUser(name, password);
		Status status = new Status();

		if (id == -1) {
			status.setStatus("Registration failed");
			status.setStatusType(StatusType.FAIL);
		} else if (id == -2) {
			status.setStatus("Your username may only contain letters and needs to have a length of minimum 5.");
			status.setStatusType(StatusType.FAIL);
		} else {
			status.setStatus("User " + name + " registered as ID " + id);
			status.setStatusType(StatusType.SUCCESS);
		}

		request.setAttribute("status", status);
		RequestDispatcher view = request.getRequestDispatcher("index.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
		view.forward(request, response);
	}
}
