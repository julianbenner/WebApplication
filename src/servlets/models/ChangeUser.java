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
public class ChangeUser {
	static DBConnection connection1;
	static Connection connection = connection1.getConnection();

	public static ChangeUserResult changeUser(String name, String oldPw, String newPw, String newPw2) {
		ChangeUserResult result;
		boolean newPasswordsDontMatch = false;
		boolean oldPasswordWrong = false;
		try {
			if(oldPw != null && newPw != null && newPw2 != null) // if password change
				if(newPw.equals(newPw2)) {
					newPasswordsDontMatch = false;
					PreparedStatement stmnt = connection.prepareStatement("SELECT * FROM Users WHERE name = ?");
					stmnt.setString(1, name);
					ResultSet rs = stmnt.executeQuery();
					while(rs.next()) {
						String hash = rs.getString("hash");
						try {
							if(PasswordHash.validatePassword(oldPw, hash)) { // if old password correct
								oldPasswordWrong = false;
								stmnt = connection.prepareStatement("UPDATE Users SET hash = ? WHERE name = ?");
								stmnt.setString(1, PasswordHash.createHash(newPw));
								stmnt.setString(2, name);
								stmnt.executeUpdate();
							} else {
								oldPasswordWrong = true;
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				} else { // new passwords don't match
					newPasswordsDontMatch = true;
				}
		} catch (SQLException e) {

		}
		result = new ChangeUserResult(oldPasswordWrong, newPasswordsDontMatch);
		return result;
	}

	public static List<Address> getAddresses(User user) throws SQLException {
		if(user == null)
			return new ArrayList<Address>();
		else
			return getAddresses(user.getId());
	}

	public static List<Address> getAddresses(int userId) throws SQLException {
		List<Address> addressList = new ArrayList<Address>();

		PreparedStatement stmnt = connection.prepareStatement("SELECT * FROM Addresses WHERE userid = ?");
		stmnt.setInt(1, userId);
		ResultSet rs = stmnt.executeQuery();
		while(rs.next()) {
			Address currentAddress = new Address();
			currentAddress.setSurname(rs.getString("surname"));
			currentAddress.setId(rs.getInt("id"));
			currentAddress.setFirstname(rs.getString("firstname"));
			currentAddress.setAddress1(rs.getString("address1"));
			currentAddress.setAddress2(rs.getString("address2"));
			currentAddress.setAddress3(rs.getString("address3"));
			currentAddress.setTown(rs.getString("town"));
			currentAddress.setPostalCode(rs.getString("postcode"));
			addressList.add(currentAddress);
		}
		return addressList;
	}
}
