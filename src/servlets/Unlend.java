package servlets;

import main.Status;
import main.StatusType;
import main.User;
import servlets.models.UnlendBook;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Unlend extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User userObj = (User) request.getSession().getAttribute("user");
		Status status = new Status();

		int id;
		try {
			id = Integer.parseInt(request.getParameter("id"));
			int unlendBookStatus = UnlendBook.unlendBook(id, userObj);
			switch (unlendBookStatus) {
				case 1: // not this particular user's lending
					status.setStatus("We could not find a lending of yours with this ID.");
					status.setStatusType(StatusType.FAIL);
					break;
				case 2: // SQL or ID error
					status.setStatus("Sorry, we could not process your request.");
					status.setStatusType(StatusType.FAIL);
					break;
				case 0:
					status.setStatus("Lending deleted!");
					status.setStatusType(StatusType.SUCCESS);
					break;
				default: // this should never happen
					status.setStatus("Something unexpected happened.");
					status.setStatusType(StatusType.INFORMATION);
			}
		} catch (Exception e) {
			status.setStatus("No or wrong lending ID!");
			status.setStatusType(StatusType.FAIL);
			e.printStackTrace();
		}

		request.setAttribute("status", status);
		RequestDispatcher view;
		view = request.getRequestDispatcher("unlend.jsp");
		view.forward(request, response);
	}
}
