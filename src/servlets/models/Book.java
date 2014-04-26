package servlets.models;

import main.Author;
import main.DBConnection;
import main.SearchResult;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Book {
	private static final Connection connection = DBConnection.getConnection();

	public static void add(String title, List<Integer> authors, String isbn, String publisher,
	                       boolean available, String description) throws SQLException {
		PreparedStatement stmnt = connection.prepareStatement(
				"INSERT INTO Books(title, isbn, publisher, available, description) VALUES(?, ?, ?, ?, ?)",
				Statement.RETURN_GENERATED_KEYS);
		stmnt.setString(1, title);
		stmnt.setString(2, isbn);
		stmnt.setString(3, publisher);
		stmnt.setBoolean(4, available);
		stmnt.setString(5, description);
		stmnt.executeUpdate();
		ResultSet rs = stmnt.getGeneratedKeys();
		rs.next();
		int id = rs.getInt(1);
		for (Integer author : authors) {
			stmnt = connection.prepareStatement("INSERT INTO Books_authors(book, author) VALUES(?, ?)");
			stmnt.setInt(1, id);
			stmnt.setInt(2, author);
			stmnt.executeUpdate();
		}
	}

	public static main.Book get(int id) throws SQLException {
		main.Book book = new main.Book();

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
		stmnt = connection.prepareStatement("SELECT * FROM Books_authors WHERE book = ?");
		stmnt.setInt(1, id);
		rs = stmnt.executeQuery();
		List<main.Author> authors = new ArrayList<>();
		while (rs.next()) {
			int authorId = rs.getInt("author");
			PreparedStatement stmnt2 = connection.prepareStatement("SELECT * FROM Authors WHERE id = ?");
			stmnt2.setInt(1, authorId);
			ResultSet rs2 = stmnt2.executeQuery();
			main.Author author = new main.Author();
			while (rs2.next()) {
				author.setSurname(rs2.getString("surname"));
				author.setFirstname(rs2.getString("firstname"));
				author.setId(rs2.getInt("id"));
			}
			authors.add(author);
		}
		book.setAuthors(authors);
		return book;
	}

	public static main.Book change(int id, String title, List<Integer> authors, String isbn, String publisher, boolean available, String description) throws SQLException {
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
		return Book.get(id);
	}

	public static boolean delete(int id) {
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

	public static SearchResult serach(String title, String surname, String firstname) throws SQLException {
		return search(title, surname, firstname, 1);
	}

	public static SearchResult search(String title, String surname, String firstname, int page) throws SQLException {
		ArrayList<main.Book> books = new ArrayList<>();

		PreparedStatement stmnt = connection.prepareStatement("SELECT a.id, a.title, a.isbn, a.available, a.description FROM (((SELECT * FROM Books WHERE UPPER(title) LIKE UPPER(?)) a INNER JOIN Books_authors ON a.id = Books_authors.book) INNER JOIN Authors ON author = Authors.id) WHERE UPPER(surname) LIKE UPPER(?) AND UPPER(firstname) LIKE UPPER(?) GROUP BY a.id, a.title, a.isbn, a.available, a.description");
		if (title == null || title.equals("")) {
			stmnt.setString(1, "%");
		} else {
			stmnt.setString(1, "%" + title + "%");
		}

		if (surname == null || surname.equals("")) {
			stmnt.setString(2, "%");
		} else {
			stmnt.setString(2, "%" + surname + "%");
		}

		if (firstname == null || firstname.equals("")) {
			stmnt.setString(3, "%");
		} else {
			stmnt.setString(3, "%" + firstname + "%");
		}

		ResultSet rs = stmnt.executeQuery();
		int resultIterator = 0;
		int pageSize = 5;
		while (rs.next()) {
			if ((page - 1) * pageSize <= resultIterator && resultIterator < page * pageSize) {
				main.Book book = new main.Book();
				book.setTitle(rs.getString("title"));
				book.setId(rs.getInt("id"));
				book.setIsbn(rs.getString("isbn"));
				book.setAvailable(rs.getBoolean("available"));
				book.setDescription(rs.getString("description"));
				List<main.Author> authors = new ArrayList<>();
				int id = rs.getInt("id");
				PreparedStatement stmnt2 = connection.prepareStatement("SELECT id, surname, firstname FROM (Authors INNER JOIN Books_authors ON Authors.id=Books_authors.author) WHERE book = ?");
				stmnt2.setInt(1, id);
				ResultSet rs2 = stmnt2.executeQuery();
				while (rs2.next()) {
					main.Author author = new Author();
					author.setId(rs2.getInt("id"));
					author.setSurname(rs2.getString("surname"));
					author.setFirstname(rs2.getString("firstname"));
					authors.add(author);
				}
				book.setAuthors(authors);
				books.add(book);
			}
			resultIterator++;
		}

		SearchResult searchResult = new SearchResult();
		searchResult.setBookArrayList(books);
		searchResult.setPages((resultIterator - 1) / pageSize + 1);
		searchResult.setCurrentPage(page);

		return searchResult;
	}
}
