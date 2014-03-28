package servlets;

import main.Book;
import main.User;
import servlets.models.Fetch;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Julian on 05/03/14.
 */
public class ShowBook extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User userObj = (User)request.getSession().getAttribute("user");
		String status = "";
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				request.setAttribute("book", Fetch.getBook(id));
			} catch (Exception e) {
				request.setAttribute("book", new Book("No book selected!"));
			}
		request.setAttribute("user", userObj);
		request.setAttribute("status", status);
		RequestDispatcher view = request.getRequestDispatcher("book.jsp");
		view.forward(request, response);
	}
}
