package servlets;

import main.DBConnection;
import main.PasswordHash;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Julian on 12/02/14.
 */
public class NewUser {
	static DBConnection connection1;
	public static int newUser(String name, String password) {
		connection1.openConnection();
		Connection connection = connection1.getConnection();
		int id = -1;
		try {
			PreparedStatement stmnt = connection.prepareStatement("INSERT INTO Users(name, hash) VALUES (?, ?)");
			stmnt.setString(1, name);
			stmnt.setString(2, PasswordHash.createHash(password));
			stmnt.executeUpdate();
			stmnt = connection.prepareStatement("SELECT ID FROM USERS WHERE NAME = ?");
			stmnt.setString(1, name);
			ResultSet rs = stmnt.executeQuery();
			while(rs.next()) {
				id = rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return id;
	}
}
