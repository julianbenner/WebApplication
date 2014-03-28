package servlets.models;

import main.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Julian on 24/02/14.
 */
public class Change {
	static Connection connection = DBConnection.getConnection();

	public static Author changeAuthor(int id, String surname, String firstname) {
		try {
			PreparedStatement stmnt = connection.prepareStatement("UPDATE Authors SET surname = ?, firstname = ? WHERE id = ?");
			stmnt.setString(1, surname);
			stmnt.setString(2, firstname);
			stmnt.setInt(3, id);
			stmnt.executeUpdate();
			return Fetch.getAuthor(id);
		} catch (SQLException e) {
			e.printStackTrace();
			return new Author();
		}
	}

	public static Book changeBook(int id, String title, List<Integer> authors, String isbn, String publisher, String description) {
		try {
			PreparedStatement stmnt = connection.prepareStatement("UPDATE Books SET title = ?, isbn = ?, publisher = ?, description = ? WHERE id = ?");
			stmnt.setString(1, title);
			stmnt.setString(2, isbn);
			stmnt.setString(3, publisher);
			stmnt.setString(4, description);
			stmnt.setInt(5, id);
			stmnt.executeUpdate();
			stmnt = connection.prepareStatement("DELETE FROM Books_authors WHERE book = ?");
			stmnt.setInt(1, id);
			stmnt.executeUpdate();
			for(Integer author: authors) {
				stmnt = connection.prepareStatement("INSERT INTO Books_authors(book, author) VALUES(?, ?)");
				stmnt.setInt(1, id);
				stmnt.setInt(2, author);
				stmnt.executeUpdate();
			}
			return Fetch.getBook(id);
		} catch (SQLException e) {
			e.printStackTrace();
			return new Book();
		}
	}
}
