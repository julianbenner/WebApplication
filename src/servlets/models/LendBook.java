package servlets.models;

import main.Book;
import main.DBConnection;
import main.User;

import java.sql.*;
import java.time.Instant;
import java.util.Calendar;

public class LendBook {
	private static final Connection connection = DBConnection.getConnection();

	private static java.sql.Date earliestFree(int bookId, int userId) throws SQLException {
		java.sql.Date lentsince = new java.sql.Date(0);
		PreparedStatement stmnt = connection.prepareStatement("SELECT user_id, lentsince, duration FROM Lendings WHERE book_id = ?");
		stmnt.setInt(1, bookId);
		ResultSet rs = stmnt.executeQuery();
		while (rs.next()) { // we iterate through each lending and set the current "lentsince" that it will be the earliest date the book is free
			int readUserId = rs.getInt("user_id");
			Date readLentsince = rs.getDate("lentsince");
			int readDuration = rs.getInt("duration");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(readLentsince);
			calendar.add(Calendar.DAY_OF_MONTH, readDuration + 1);
			lentsince = userId == readUserId ? null : new java.sql.Date(calendar.getTime().getTime());
		}
		return lentsince;
	}

	/**
	 * Inserts a row into the lendings table.
	 *
	 * @param bookId    The book to be lent
	 * @param userId    The user lendings
	 * @param lentsince java.sql.Date of beginning date
	 * @param duration  Lending duration in days
	 * @throws SQLException if values are invalid or database connection is not established
	 */
	private static void createLending(int bookId, int userId, java.sql.Date lentsince, int duration) throws SQLException {
		PreparedStatement stmnt = connection.prepareStatement("INSERT INTO Lendings(book_id, user_id, lentsince, duration) VALUES(?, ?, ?, ?)");
		stmnt.setInt(1, bookId);
		stmnt.setInt(2, userId);
		stmnt.setDate(3, lentsince);
		stmnt.setInt(4, duration);
		stmnt.executeUpdate();
	}

	public static int lendBook(Book book, User user) throws SQLException {
		if (user == null)
			return -1;

		int status;
		int duration = user.getGroup() == 4 ? 7 : 14;
		java.sql.Date lentsince = new java.sql.Date(0);
		PreparedStatement stmnt;
		if (book.isAvailable()) {
			lentsince = new java.sql.Date(java.util.Date.from(Instant.now()).getTime());
			connection.prepareStatement("UPDATE Books SET available=false WHERE id=" + String.valueOf(book.getId())).executeUpdate();
			status = 0; // book available right now
		} else {
			lentsince = earliestFree(book.getId(), user.getId());
			status = lentsince == null ? -2 : (int) ((lentsince.getTime() - java.sql.Date.from(Instant.now()).getTime()) / 86400000);
			if (status != -2) {
				int timeFromNow = (int) ((lentsince.getTime() - java.sql.Date.from(Instant.now()).getTime()) / 86400000);
				if (timeFromNow < 0)
					status = -3; // book is not lent, but also not available
			}
		}

		if (status >= 0)
			createLending(book.getId(), user.getId(), lentsince, duration);

		return status;
	}
}
