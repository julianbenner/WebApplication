package servlets;

import main.Book;
import main.Status;
import main.StatusType;
import main.User;
import servlets.models.Fetch;
import servlets.models.LendBook;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Lend extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User userObj = (User) request.getSession().getAttribute("user");
		Status status = new Status();

		if (userObj != null) {
			int id;
			try {
				id = Integer.parseInt(request.getParameter("id"));
				Book book = Fetch.getBook(id);
				LendBook.lendBook(book, userObj);
			} catch (Exception e) {
				status.setStatus("No or wrong book ID!");
				status.setStatusType(StatusType.FAIL);
				e.printStackTrace();
			}
		} else {
			status.setStatus("You need to be logged in to perform this action.");
			status.setStatusType(StatusType.FAIL);
		}
		int id = -1;
		request.setAttribute("status", status);
		RequestDispatcher view;
		view = request.getRequestDispatcher("index.jsp");
		view.forward(request, response);
	}
}
