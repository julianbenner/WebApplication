package servlets.models;

import main.Author;
import main.Book;
import main.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Change {
	private static final Connection connection = DBConnection.getConnection();

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

	public static Book changeBook(int id, String title, List<Integer> authors, String isbn, String publisher, boolean available, String description) {
		try {
			PreparedStatement stmnt = connection.prepareStatement("UPDATE Books SET title = ?, isbn = ?, publisher = ?, available = ?, description = ? WHERE id = ?");
			stmnt.setString(1, title);
			stmnt.setString(2, isbn);
			stmnt.setString(3, publisher);
			stmnt.setBoolean(4, available);
			stmnt.setString(5, description);
			stmnt.setInt(6, id);
			stmnt.executeUpdate();
			stmnt = connection.prepareStatement("DELETE FROM Books_authors WHERE book = ?");
			stmnt.setInt(1, id);
			stmnt.executeUpdate();
			for (Integer author : authors) {
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
