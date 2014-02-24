package servlets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import main.*;

/**
 * Created by Julian on 09/02/14.
 */
public class VerifyLogin {
	static DBConnection connection1;
	public static User verifyLogin(String name, String password) {
		//HttpSession session = null;
		User userObj = null;
		Connection connection = connection1.getConnection();
		try {
			PreparedStatement stmnt = connection.prepareStatement("SELECT * FROM Users WHERE name = ?");
			stmnt.setString(1, name);
			ResultSet rs = stmnt.executeQuery();
			while(rs.next()) {
				String hash = rs.getString("hash");
				try {
					if(PasswordHash.validatePassword(password, hash))
						userObj = new User(rs.getString("name"), rs.getInt("id"));
				} catch (Exception e) {

				}
			}
			if(userObj != null) {

			}
		} catch (SQLException e) {

		}

		return userObj;
	}
}
