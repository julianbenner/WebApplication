package servlets.models;

import main.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
	private static final Connection connection = DBConnection.getConnection();

	public static main.User get(int id) {
		//HttpSession session = null;
		main.User userObj = null;
		try {
			PreparedStatement stmnt = connection.prepareStatement("SELECT * FROM Users WHERE id = ?");
			stmnt.setInt(1, id);
			ResultSet rs = stmnt.executeQuery();
			while (rs.next()) {
				try {
					userObj = new main.User();
					userObj.setName(rs.getString("name"));
					userObj.setId(rs.getInt("id"));
					userObj.setGroup(rs.getInt("usergroup"));
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
