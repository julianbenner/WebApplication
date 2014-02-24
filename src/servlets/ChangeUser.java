package servlets;

import main.ChangeUserResult;
import main.DBConnection;
import main.PasswordHash;
import main.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Julian on 24/02/14.
 */
public class ChangeUser {
	static DBConnection connection1;
	public static ChangeUserResult changeUser(String name, String oldPw, String newPw, String newPw2) {
		ChangeUserResult result;
		boolean newPasswordsDontMatch = false;
		boolean oldPasswordWrong = false;
		User userObj = null;
		Connection connection = connection1.getConnection();
		try {
			if(oldPw != null && newPw != null && newPw2 != null) // if password change
				if(newPw.equals(newPw2)) {
					newPasswordsDontMatch = false;
					PreparedStatement stmnt = connection.prepareStatement("SELECT * FROM Users WHERE name = ?");
					stmnt.setString(1, name);
					ResultSet rs = stmnt.executeQuery();
					while(rs.next()) {
						String hash = rs.getString("hash");
						try {
							if(PasswordHash.validatePassword(oldPw, hash)) { // if old password correct
								oldPasswordWrong = false;
								stmnt = connection.prepareStatement("UPDATE Users SET hash = ? WHERE name = ?");
								stmnt.setString(1, PasswordHash.createHash(newPw));
								stmnt.setString(2, name);
								stmnt.executeUpdate();
							} else {
								oldPasswordWrong = true;
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				} else { // new passwords don't match
					newPasswordsDontMatch = true;
				}
		} catch (SQLException e) {

		}
		result = new ChangeUserResult(oldPasswordWrong, newPasswordsDontMatch);
		return result;
	}
}
