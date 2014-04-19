package servlets.models;

import main.Author;
import main.Book;
import main.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Search {
	private static final Connection connection = DBConnection.getConnection();

	public static Author getAuthor(int id) throws SQLException {
		Author author = new Author();

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

	public static List<Book> searchBook(String title, String surname, String firstname) throws SQLException {
		List<Book> books = new ArrayList<>();

		PreparedStatement stmnt = connection.prepareStatement("SELECT a.id, a.title FROM (((SELECT * FROM Books WHERE UPPER(title) LIKE UPPER(?)) a INNER JOIN Books_authors ON a.id = Books_authors.book) INNER JOIN Authors ON author = Authors.id) WHERE UPPER(surname) LIKE UPPER(?) AND UPPER(firstname) LIKE UPPER(?) GROUP BY a.id, a.title");
		if (title == null || title.equals(""))
			stmnt.setString(1, "%");
		else
			stmnt.setString(1, "%" + title + "%");
		if (surname == null || surname.equals(""))
			stmnt.setString(2, "%");
		else
			stmnt.setString(2, surname);
		if (firstname == null || firstname.equals(""))
			stmnt.setString(3, "%");
		else
			stmnt.setString(3, firstname);
		ResultSet rs = stmnt.executeQuery();
		while (rs.next()) {
			Book book = new Book();
			book.setTitle(rs.getString("title"));
			book.setId(rs.getInt("id"));
			List<Author> authors = new ArrayList<>();
			int id = rs.getInt("id");
			PreparedStatement stmnt2 = connection.prepareStatement("SELECT surname, firstname FROM (Authors INNER JOIN Books_authors ON Authors.id=Books_authors.author) WHERE book = ?");
			stmnt2.setInt(1, id);
			ResultSet rs2 = stmnt2.executeQuery();
			while (rs2.next()) {
				Author author = new Author();
				author.setSurname(rs2.getString("surname"));
				author.setFirstname(rs2.getString("firstname"));
				authors.add(author);
			}
			book.setAuthors(authors);
			books.add(book);
		}

		return books;
	}
}
