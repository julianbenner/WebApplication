package servlets.models;

import main.Author;
import main.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchAuthors {
	private static Connection connection = DBConnection.getConnection();

	public static List<Author> searchAuthors(String query) throws SQLException {
		PreparedStatement stmnt = connection.prepareStatement("SELECT * FROM Authors WHERE UPPER(surname) LIKE UPPER(?)");
		stmnt.setString(1, query);
		ResultSet rs = stmnt.executeQuery();
		List<Author> authors = new ArrayList<>();
		while(rs.next()) {
			Author author = new Author();
			author.setSurname(rs.getString("surname"));
			author.setFirstname(rs.getString("firstname"));
			author.setId(rs.getInt("id"));
			authors.add(author);
		}
		return authors;
	}
}
