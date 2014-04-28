package servlets;

import main.Status;
import main.StatusType;
import main.Token;
import main.User;
import servlets.models.VerifyLogin;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;

public class LoginController extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		User userObj = VerifyLogin.verifyLogin(name, password);
		Status status = new Status();

		if (userObj == null) {
			status.setStatus("Sorry, username or password were wrong.");
			status.setStatusType(StatusType.FAIL);
		} else {
			request.getSession().setAttribute("user", userObj);
			if (request.getParameter("staylogin") != null) {
				Cookie cookie1 = new Cookie("id", String.valueOf(userObj.getId()));
				Cookie cookie2 = new Cookie("token", Token.getToken(request.getSession()));
				cookie1.setMaxAge(60 * 60 * 24);
				cookie2.setMaxAge(60 * 60 * 24);
				VerifyLogin.setLoginCookie(userObj.getId(), Token.getToken(request.getSession()));
				response.addCookie(cookie1);
				response.addCookie(cookie2);
			}
			status.setStatus("Login successful as " + name);
			status.setStatusType(StatusType.SUCCESS);
		}

		request.setAttribute("status", status);
		String referer = new URL(request.getHeader("referer")).getFile();
	/*	String[] splitReferer = referer.split("/");
		String filename = splitReferer[splitReferer.length - 1];
		if (splitReferer.length == 3) filename = "index.jsp";*/
		RequestDispatcher view = request.getRequestDispatcher("empty.jsp");
		request.setAttribute("referer1", referer);
		view.forward(request, response);
	}
}
