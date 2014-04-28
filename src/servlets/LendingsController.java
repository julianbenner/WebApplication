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

public class LendingsController extends HttpServlet {
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

		String action = request.getParameter("action");
		int id = 0;

		if (userObj != null) {
			if (action == null || (!action.equals("book") && !action.equals("user") && !action.equals("open"))) {
				action = "user"; // default to user lendings if no or unknown action given
			}
			try {
				id = Integer.parseInt(request.getParameter("id"));
			} catch (NullPointerException | NumberFormatException e) {
				if (action.equals("user")) {
					id = userObj.getId();
				}
				e.printStackTrace();
			}
			if (!userObj.isAdmin()) {
				if (action.equals("open")) {
					action = "user";
				}
				if (action.equals("user")) {
					id = userObj.getId();
				}
			}
			try {
				if (action.equals("user")) { // user lendings
					request.setAttribute("lendings", servlets.models.Lending.getUserLendings(id));
					request.setAttribute("userO", servlets.models.User.get(id));
					view = request.getRequestDispatcher("lendings_by_user.jsp");
				} else if (action.equals("book")) { // book lendings
					request.setAttribute("lendings", servlets.models.Lending.getBookLendings(id));
					request.setAttribute("book", id);
					view = request.getRequestDispatcher("lendings_by_book.jsp");
				} else { // open lendings
					request.setAttribute("lendings", servlets.models.Lending.getOpenLendings());
					view = request.getRequestDispatcher("lendings_open.jsp");
				}
			} catch (Exception e) {
				status.setStatusType(StatusType.FAIL);
				status.setStatus("No or wrong ID!");
				view = request.getRequestDispatcher("empty.jsp");

			}
			request.setAttribute("user", userObj);
		} else {
			status.setStatus("You need to be logged in to do this.");
			status.setStatusType(StatusType.FAIL);
			view = request.getRequestDispatcher("empty.jsp");
		}

		request.setAttribute("status", status);
		view.forward(request, response);
	}
}
