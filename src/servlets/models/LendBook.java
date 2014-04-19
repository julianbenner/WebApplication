package servlets.models;

import main.Book;
import main.DBConnection;
import main.User;

import java.sql.*;
import java.util.Calendar;

public class LendBook {
	private static final Connection connection = DBConnection.getConnection();

	public static int lendBook(Book book, User user) throws SQLException {
		int status = 1;
		int duration = user.getGroup() == 4 ? 7 : 14;
		java.sql.Date lentsince = new java.sql.Date(0);
		PreparedStatement stmnt;
		if (book.isAvailable()) {

		} else {
			stmnt = connection.prepareStatement("SELECT user_id, lentsince, duration FROM Lendings WHERE book_id = ?");
			stmnt.setInt(1, book.getId());
			ResultSet rs = stmnt.executeQuery();
			while (rs.next()) { // we iterate through each lending and set the current "lentsince" that it will be the earliest date the book is free
				int readUserId = rs.getInt("user_id");
				Date readLentsince = rs.getDate("lentsince");
				int readDuration = rs.getInt("duration");
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(readLentsince);
				calendar.add(Calendar.DAY_OF_MONTH, readDuration + 1);
				lentsince = new java.sql.Date(calendar.getTime().getTime());
			}
		}

		stmnt = connection.prepareStatement("INSERT INTO Lendings(book_id, user_id, lentsince, duration) VALUES(?, ?, ?, ?)");
		stmnt.setInt(1, book.getId());
		stmnt.setInt(2, user.getId());
		stmnt.setDate(3, lentsince);
		stmnt.setInt(4, duration);
		stmnt.executeUpdate();
		return status;
	}
}
