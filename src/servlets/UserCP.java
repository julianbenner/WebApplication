package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import main.*;

import java.io.IOException;

/**
 * Created by Julian on 09/02/14.
 */
public class UserCP extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User userObj = (User)request.getSession().getAttribute("user");
		ChangeUserResult result;
		String status = "Changes were applied. If inputs are red, check and re-submit them.";
		if(userObj == null) {
			status = "Please log in first.";
			result = null;
		} else {
			request.getSession().setAttribute("user", userObj);
			request.setAttribute("userObj", userObj);

			String oldPw = request.getParameter("oldPw");
			String newPw = request.getParameter("newPw");
			String newPw2 = request.getParameter("newPw2");

			result = ChangeUser.changeUser(userObj.getName(), oldPw, newPw, newPw2);
		}
		request.setAttribute("status", status);
		request.setAttribute("result", result);
		RequestDispatcher view = request.getRequestDispatcher("usercp.jsp");
		view.forward(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User userObj = (User)request.getSession().getAttribute("user");
		String status = "";
		if(userObj == null) {
			status = "Please log in first.";
		} else {
			request.getSession().setAttribute("user", userObj);
			request.setAttribute("userObj", userObj);
		}
		request.setAttribute("status", status);
		RequestDispatcher view = request.getRequestDispatcher("usercp.jsp");
		view.forward(request, response);
	}
}
