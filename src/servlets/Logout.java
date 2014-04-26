package servlets;

import main.User;
import servlets.models.VerifyLogin;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Logout extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			VerifyLogin.invalidateLoginCookie(((User) request.getSession().getAttribute("user")).getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getSession().invalidate();
		response.sendRedirect("index.jsp");
	}
}