package servlets;

import main.Status;
import main.StatusType;
import main.Token;
import main.User;
import servlets.models.Author;
import servlets.models.VerifyLogin;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteAuthorController extends HttpServlet {
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

		int id = -1;
		if (userObj != null && userObj.isAdmin()) {
			try {
				id = Integer.parseInt(request.getParameter("id"));
				request.setAttribute("author", Author.get(id));
			} catch (Exception e) {
				request.setAttribute("author", new Author());
				status.setStatus("No or wrong author ID!");
				status.setStatusType(StatusType.FAIL);
			}
			String token = request.getParameter("token"); // get token from GET/POST
			if (token == null || !token.equals(Token.getToken(request.getSession()))) {
				token = Token.getToken(request.getSession());
				request.setAttribute("token", token);
				view = request.getRequestDispatcher("delete_author.jsp");
			} else { // does token correspond to user?
				if (Author.delete(id)) { // if so, try to delete author
					status.setStatus("Deletion successful!");
					status.setStatusType(StatusType.SUCCESS);
					view = request.getRequestDispatcher("empty.jsp");
				} else {
					status.setStatus("Deletion unsuccessful!");
					status.setStatusType(StatusType.FAIL);
					view = request.getRequestDispatcher("delete_failed.jsp");
				}
			}
		} else {
			status.setStatus("You need to be logged in as admin to do this.");
			status.setStatusType(StatusType.FAIL);
			view = request.getRequestDispatcher("empty.jsp");
		}
		request.setAttribute("user", userObj);
		request.setAttribute("status", status);
		view.forward(request, response);
	}
}
