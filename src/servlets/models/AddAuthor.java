package servlets.models;

import main.DBConnection;

import java.sql.*;
import java.util.List;

public class AddAuthor {
	private static Connection connection = DBConnection.getConnection();

	public static int addAuthor(String surname, String firstname) throws SQLException {
		PreparedStatement stmnt = connection.prepareStatement("INSERT INTO Authors(surname, firstname) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);
		stmnt.setString(1, surname);
		stmnt.setString(2, firstname);
		stmnt.executeUpdate();
		ResultSet rs = stmnt.getGeneratedKeys();
		rs.next();
		return rs.getInt(1);
	}
}
