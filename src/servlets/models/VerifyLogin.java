package servlets.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import main.*;

/**
 * Created by Julian on 09/02/14.
 */
public class VerifyLogin {
	private static Connection connection = DBConnection.getConnection();
	public static User verifyLogin(String name, String password) {
		//HttpSession session = null;
		User userObj = null;
		try {
			PreparedStatement stmnt = connection.prepareStatement("SELECT * FROM Users WHERE name = ?");
			stmnt.setString(1, name);
			ResultSet rs = stmnt.executeQuery();
			while(rs.next()) {
				String hash = rs.getString("hash");
				try {
					if(PasswordHash.validatePassword(password, hash)) {
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
