package servlets.models;

import main.Author;
import main.Book;
import main.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Fetch {
	private static Connection connection = DBConnection.getConnection();

	public static Author getAuthor(int id) throws SQLException {
		Author author = new Author();

		PreparedStatement stmnt = connection.prepareStatement("SELECT * FROM Authors WHERE id = ?");
		stmnt.setInt(1, id);
		ResultSet rs = stmnt.executeQuery();
		while(rs.next()) {
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
		while(rs.next()) {
			book.setTitle(rs.getString("title"));
			book.setId(rs.getInt("id"));
			book.setIsbn(rs.getString("isbn"));
			book.setPublisher(rs.getString("publisher"));
			book.setShelf(rs.getInt("shelf"));
			book.setDescription(rs.getString("description"));
		}
		stmnt = connection.prepareStatement("SELECT * FROM Books_authors WHERE book = ?");
		stmnt.setInt(1, id);
		rs = stmnt.executeQuery();
		List<Author> authors = new ArrayList<>();
		while(rs.next()) {
			int authorId = rs.getInt("author");
			PreparedStatement stmnt2 = connection.prepareStatement("SELECT * FROM Authors WHERE id = ?");
			stmnt2.setInt(1, authorId);
			ResultSet rs2 = stmnt2.executeQuery();
			Author author = new Author();
			while(rs2.next()) {
				author.setSurname(rs2.getString("surname"));
				author.setFirstname(rs2.getString("firstname"));
				author.setId(rs2.getInt("id"));
			}
			authors.add(author);
		}
		book.setAuthors(authors);
		return book;
	}
}
