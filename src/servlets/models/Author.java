package servlets.models;

import main.Book;
import main.DBConnection;

import java.sql.*;

public class Author {
	private static final Connection connection = DBConnection.getConnection();

	public static main.Author get(int id) throws SQLException {
		main.Author author = new main.Author();

		PreparedStatement stmnt = connection.prepareStatement("SELECT * FROM Authors WHERE id = ?");
		stmnt.setInt(1, id);
		ResultSet rs = stmnt.executeQuery();
		while (rs.next()) {
			author.setSurname(rs.getString("surname"));
			author.setFirstname(rs.getString("firstname"));
			author.setId(rs.getInt("id"));
		}
		return author;
	}

	public static Book getBook(int id) throws SQLException {
		Book book = new Book();

		PreparedStatement stmnt = connection.prepareStatement("SELECT * FROM Books WHERE id = ?");
		stmnt.setInt(1, id);
		ResultSet rs = stmnt.executeQuery();
		while (rs.next()) {
			book.setTitle(rs.getString("title"));
			book.setId(rs.getInt("id"));
			book.setIsbn(rs.getString("isbn"));
			book.setPublisher(rs.getString("publisher"));
			book.setAvailable(rs.getBoolean("available"));
			book.setShelf(rs.getInt("shelf"));
			book.setDescription(rs.getString("description"));
		}
		return book;
	}

	public static main.Author change(int id, String surname, String firstname) throws SQLException {
		PreparedStatement stmnt = connection.prepareStatement("UPDATE Authors SET surname = ?, firstname = ? WHERE id = ?");
		stmnt.setString(1, surname);
		stmnt.setString(2, firstname);
		stmnt.setInt(3, id);
		stmnt.executeUpdate();
		return Author.get(id);
	}

	public static boolean delete(int id) {
		try {
			PreparedStatement stmnt = connection.prepareStatement("DELETE FROM Authors WHERE id = ?");
			stmnt.setInt(1, id);
			stmnt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static int add(String surname, String firstname) throws SQLException {
		PreparedStatement stmnt = connection.prepareStatement("INSERT INTO Authors(surname, firstname) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);
		stmnt.setString(1, surname);
		stmnt.setString(2, firstname);
		stmnt.executeUpdate();
		ResultSet rs = stmnt.getGeneratedKeys();
		rs.next();
		return rs.getInt(1);
	}
}
