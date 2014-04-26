package servlets.models;

import main.DBConnection;
import main.PasswordHash;
import main.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VerifyLogin {
	private static final Connection connection = DBConnection.getConnection();

	public static User getUser(HttpServletRequest request) {
		User userObj = (User) request.getSession().getAttribute("user");
		if (userObj == null) {
			VerifyLogin.verifyCookie(request);
			userObj = (User) request.getSession().getAttribute("user");
		}

		return userObj;
	}

	public static void invalidateLoginCookie(int userId) {
		setLoginCookie(userId, "");
	}

	public static void setLoginCookie(int userId, String cookie) {
		try {
			PreparedStatement stmnt = connection.prepareStatement("UPDATE users SET cookie=? WHERE id=?");
			stmnt.setString(1, cookie);
			stmnt.setInt(2, userId);
			stmnt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static User verifyCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		int userId = 0;
		User userObj = null;
		String token = null;
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("id")) {
				userId = Integer.parseInt(cookie.getValue());
			} else if (cookie.getName().equals("token")) {
				token = cookie.getValue();
			}
		}
		boolean validCookie = false;
		if (userId != 0 && token != null) {
			try {
				PreparedStatement stmnt = connection.prepareStatement("SELECT cookie FROM Users WHERE id = ?");
				stmnt.setInt(1, userId);
				ResultSet rs = stmnt.executeQuery();
				while (rs.next()) {
					if (rs.getString("cookie").equals(token)) {
						validCookie = true;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (validCookie) {
				try {
					PreparedStatement stmnt = connection.prepareStatement("SELECT * FROM Users WHERE id = ?");
					stmnt.setInt(1, userId);
					ResultSet rs = stmnt.executeQuery();
					while (rs.next()) {
						userObj = new User();
						userObj.setName(rs.getString("name"));
						userObj.setId(rs.getInt("id"));
						userObj.setGroup(rs.getInt("usergroup"));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		request.getSession().setAttribute("user", userObj);
		return userObj;
	}

	public static User verifyLogin(String name, String password) {
		//HttpSession session = null;
		User userObj = null;
		try {
			PreparedStatement stmnt = connection.prepareStatement("SELECT * FROM Users WHERE name = ?");
			stmnt.setString(1, name);
			ResultSet rs = stmnt.executeQuery();
			while (rs.next()) {
				String hash = rs.getString("hash");
				try {
					if (PasswordHash.validatePassword(password, hash)) {
						userObj = new User();
						userObj.setName(rs.getString("name"));
						userObj.setId(rs.getInt("id"));
						userObj.setGroup(rs.getInt("usergroup"));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return userObj;
	}
}
