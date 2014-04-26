package servlets;

import main.Book;
import main.Status;
import main.StatusType;
import main.User;
import servlets.models.Lending;
import servlets.models.VerifyLogin;

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
		User userObj = VerifyLogin.getUser(request);
		Status status = new Status();
		int lendId = 0;

		int id;
		try {
			id = Integer.parseInt(request.getParameter("id"));
			Book book = servlets.models.Book.get(id);
			request.setAttribute("bookId", book.getId());
			int[] lendBook = Lending.lendBook(book, userObj);
			lendId = lendBook[1];
			int lendBookStatus = lendBook[0];
			switch (lendBookStatus) {
				case -1: // not logged in
					status.setStatus("You need to be logged in to perform this action.");
					status.setStatusType(StatusType.FAIL);
					break;
				case -2: // user already has latest lending period
					status.setStatus("Sorry, you can't append a lending on a book you currently own.");
					status.setStatusType(StatusType.FAIL);
					break;
				case -3: // book is unavailable and whereabouts are unknown
					status.setStatus("Sorry, we don't know where this book is. Please contact us if this problem persists.");
					status.setStatusType(StatusType.FAIL);
					break;
				case 0:
					status.setStatus("Lending accepted!");
					status.setStatusType(StatusType.SUCCESS);
					break;
				default: // book not immediately available
					status.setStatus("The book is not available right now. It was reserved for you to fetch it in " + String.valueOf(lendBookStatus) + " days.");
					status.setStatusType(StatusType.INFORMATION);
			}
		} catch (Exception e) {
			status.setStatus("No or wrong book ID!");
			status.setStatusType(StatusType.FAIL);
			e.printStackTrace();
		}

		request.setAttribute("status", status);
		request.setAttribute("lendId", lendId);
		RequestDispatcher view;
		view = request.getRequestDispatcher("lend.jsp");
		view.forward(request, response);
	}
}
