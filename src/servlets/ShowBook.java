package servlets;

import main.Address;
import main.Book;
import main.User;
import servlets.models.ChangeUser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Julian on 05/03/14.
 */
public class ShowBook extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String status = "";
			try {
				request.setAttribute("book", new Book("lel"));
			} catch (Exception e) {
				request.setAttribute("book", new Book("No book selected!"));
			}
		request.setAttribute("status", status);
		RequestDispatcher view = request.getRequestDispatcher("book.jsp");
		view.forward(request, response);
	}
}
