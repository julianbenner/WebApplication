package servlets.models;

import main.DBConnection;

import java.sql.*;
import java.util.List;

public class AddBook {
	private static Connection connection = DBConnection.getConnection();

	public static void addBook(String title, List<Integer> authors) throws SQLException {
		PreparedStatement stmnt = connection.prepareStatement("INSERT INTO Books(title) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
		stmnt.setString(1, title);
		stmnt.executeUpdate();
		ResultSet rs = stmnt.getGeneratedKeys();
		rs.next();
		int id = rs.getInt(1);
		for(Integer author: authors) {
			stmnt = connection.prepareStatement("INSERT INTO Books_authors(book, author) VALUES(?, ?)");
			stmnt.setInt(1, id);
			stmnt.setInt(2, author);
			stmnt.executeUpdate();
		}
	}
}
