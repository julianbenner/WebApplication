package servlets.models;

import main.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Delete {
	private static final Connection connection = DBConnection.getConnection();

	public static boolean deleteAuthor(int id, String surname, String firstname) {
		try {
			PreparedStatement stmnt = connection.prepareStatement("UPDATE Authors SET surname = ?, firstname = ? WHERE id = ?");
			stmnt.setString(1, surname);
			stmnt.setString(2, firstname);
			stmnt.setInt(3, id);
			stmnt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean deleteBook(int id) {
		try {
			PreparedStatement stmnt = connection.prepareStatement("DELETE FROM Books WHERE id = ?");
			stmnt.setInt(1, id);
			stmnt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
