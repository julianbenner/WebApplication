package servlets;

import main.Status;
import main.StatusType;
import main.User;
import servlets.models.Author;
import servlets.models.VerifyLogin;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorController extends HttpServlet {
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

		try {
			int id = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("author", Author.get(id));
			view = request.getRequestDispatcher("author.jsp");
		} catch (Exception e) {
			status.setStatusType(StatusType.FAIL);
			status.setStatus("No or wrong author ID!");
			view = request.getRequestDispatcher("empty.jsp");
		}

		request.setAttribute("user", userObj);
		request.setAttribute("status", status);

		view.forward(request, response);
	}
}
