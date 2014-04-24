package servlets.models;

import main.DBConnection;
import main.PasswordHash;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;

public class NewUser {
	private static final Connection connection = DBConnection.getConnection();

	public static int newUser(String name, String password) {
		int id = -1;
		try {
			if (!name.matches("^[a-z\\d\\.]{5,}$")) {
				id = -2;
				throw new InputMismatchException();
			}
			PreparedStatement stmnt = connection.prepareStatement("INSERT INTO Users(name, hash, usergroup) VALUES (?, ?, 3)");
			stmnt.setString(1, name);
			stmnt.setString(2, PasswordHash.createHash(password));
			stmnt.executeUpdate();
			stmnt = connection.prepareStatement("SELECT ID FROM USERS WHERE NAME = ?");
			stmnt.setString(1, name);
			ResultSet rs = stmnt.executeQuery();
			while (rs.next()) {
				id = rs.getInt("id");
			}
		} catch (InputMismatchException | SQLException | InvalidKeySpecException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return id;
	}
}
