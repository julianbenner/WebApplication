package servlets.models;

import com.sun.istack.internal.Nullable;
import main.Book;
import main.DBConnection;
import main.User;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * This class performs lending logic and database operations.
 *
 * @author Julian Benner
 * @version 0.9
 */
public class Lending {
	private static final Connection connection = DBConnection.getConnection();

	/**
	 * Calculates the date the given book will be available. If already reserved by a user, returns null.
	 *
	 * @param bookId Book to be checked.
	 * @param userId User wanting to lend the book.
	 * @return java.sql.Date of when the book is available.
	 * @throws SQLException when database connection fails or bookId invalid.
	 */
	@Nullable
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
	private static int createLending(int bookId, int userId, java.sql.Date lentsince, int duration) throws SQLException {
		PreparedStatement stmnt = connection.prepareStatement("INSERT INTO Lendings(book_id, user_id, lentsince, duration, collected) VALUES(?, ?, ?, ?, false)", Statement.RETURN_GENERATED_KEYS);
		stmnt.setInt(1, bookId);
		stmnt.setInt(2, userId);
		stmnt.setDate(3, lentsince);
		stmnt.setInt(4, duration);
		stmnt.executeUpdate();
		ResultSet rs = stmnt.getGeneratedKeys();
		rs.next();
		return rs.getInt(1);
	}

	/**
	 * Lends a book for a certain user for the earliest possible date.
	 *
	 * @param book The book to be lent.
	 * @param user The user wanting to lend.
	 * @return Returns an int-array, [0] being the status and [1] being the ID of the lending in the database
	 * @throws SQLException when database connection fails.
	 */
	public static int[] lendBook(Book book, User user) throws SQLException {
		int status;
		int lendId = 0;
		if (user == null)
			status = -1;
		else {
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
				lendId = createLending(book.getId(), user.getId(), lentsince, duration);
		}

		int[] returnArray = new int[2];
		returnArray[0] = status;
		returnArray[1] = lendId;

		return returnArray;
	}

	public static ArrayList<main.Lending> getUserLendings(int id) {
		ArrayList<main.Lending> lendingArrayList = new ArrayList<>();
		ArrayList<Integer> lendingIdArrayList = new ArrayList<>();

		try {
			PreparedStatement stmnt = connection.prepareStatement("SELECT id FROM Lendings WHERE user_id = ?");
			stmnt.setInt(1, id);
			ResultSet rs = stmnt.executeQuery();
			while (rs.next()) {
				lendingIdArrayList.add(rs.getInt("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		for (int i : lendingIdArrayList) {
			lendingArrayList.add(getLending(i));
		}

		return lendingArrayList;
	}

	public static main.Lending getLending(int id) {
		main.Lending lending = new main.Lending();

		try {
			PreparedStatement stmnt = connection.prepareStatement("SELECT * FROM Lendings WHERE id = ?");
			stmnt.setInt(1, id);
			ResultSet rs = stmnt.executeQuery();
			while (rs.next()) {
				lending.setId(rs.getInt("id"));
				lending.setDuration(rs.getInt("duration"));
				lending.setBook(servlets.models.Book.get(rs.getInt("book_id")));
				lending.setUser(rs.getInt("user_id"));
				lending.setDate(rs.getDate("lentsince"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lending;
	}
}
