package servlets.models;

import main.DBConnection;
import main.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class provides functionality for revoking lendings.
 *
 * @author Julian Benner
 * @version 0.9
 */
public class UnlendBook {
	private static final Connection connection = DBConnection.getConnection();

	/**
	 * Lends a book for a certain user for the earliest possible date.
	 *
	 * @param id   The lending to be removed.
	 * @param user The user wanting to unlend.
	 * @return Returns an int success code, 0 being success and other numbers errors
	 */
	public static int unlendBook(int id, User user) {
		try {
			PreparedStatement stmnt = connection.prepareStatement("SELECT user_id, book_id FROM Lendings WHERE id = ?");
			stmnt.setInt(1, id);
			ResultSet rs = stmnt.executeQuery();
			rs.next();
			int bookId = rs.getInt(2);
			// TODO: implement tokens
			// TODO: implement fullfilled check
			if (rs.getInt(1) != user.getId()) { // if user wants to unlend foreign book
				return 1;
			}
			connection.prepareStatement("UPDATE Books SET available=true WHERE id=" + String.valueOf(bookId)).executeUpdate();
			stmnt = connection.prepareStatement("DELETE FROM Lendings WHERE id = ?");
			stmnt.setInt(1, id);
			stmnt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 2;
		}

		return 0;
	}
}
