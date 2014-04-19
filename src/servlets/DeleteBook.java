package servlets;

import main.Book;
import main.Status;
import main.StatusType;
import main.User;
import servlets.models.Delete;
import servlets.models.Fetch;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteBook extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User userObj = (User) request.getSession().getAttribute("user");
		Status status = new Status();
		int id = -1;
		try {
			id = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("book", Fetch.getBook(id));
		} catch (Exception e) {
			request.setAttribute("book", new Book());
			status.setStatus("No or wrong book ID!");
			status.setStatusType(StatusType.FAIL);
		}
		request.setAttribute("user", userObj);
		request.setAttribute("status", status);
		RequestDispatcher view;
		view = request.getRequestDispatcher("index.jsp");
		if (userObj.isAdmin()) {
			String asd = request.getParameter("confirm");
			if (request.getParameter("confirm") != null && request.getParameter("confirm").equals("1")) {
				if (Delete.deleteBook(id))
					view = request.getRequestDispatcher("delete_book_success.jsp");
				else
					view = request.getRequestDispatcher("delete_book_failed.jsp");
			} else {
				view = request.getRequestDispatcher("delete_book.jsp");
			}
		}
		view.forward(request, response);
	}
}
