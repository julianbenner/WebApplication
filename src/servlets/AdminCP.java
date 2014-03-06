package servlets;

import main.Address;
import main.ChangeUserResult;
import main.User;
import servlets.models.AddBook;
import servlets.models.ChangeUser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminCP extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processReguest(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processReguest(request, response);
	}

	public void processReguest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User userObj = (User)request.getSession().getAttribute("user");
		RequestDispatcher view;
		String status = "";
		if(userObj == null) {
			status = "Please log in first.";
			view = request.getRequestDispatcher("index.jsp");
		} else {
			String action = request.getParameter("action");
			if(action == null)
				action = "";
			switch (action) {
				case "addbook":
					view = request.getRequestDispatcher("add_book.jsp");
					break;
				case "addbook2":
					String title = request.getParameter("title");
					String[] authors = request.getParameterValues("author");
					List<Integer> authorsInt = new ArrayList<>();
					for(String author: authors) {
						try {
							authorsInt.add(Integer.parseInt(author));
						} catch (NumberFormatException e) {
							e.printStackTrace();
						}
					}
					try {
						AddBook.addBook(title, authorsInt);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					view = request.getRequestDispatcher("add_book.jsp");
					break;
				default:
					view = request.getRequestDispatcher("admin_overview.jsp");
			}
		}
		request.setAttribute("status", status);
		view.forward(request, response);
	}
}
