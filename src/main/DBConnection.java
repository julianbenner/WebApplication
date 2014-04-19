package main;

import org.apache.derby.jdbc.EmbeddedDriver;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static final Driver driver = new EmbeddedDriver();
	private static Connection connection;
	private static String username = "";
	private static String password = "";
	private static boolean isConnected = false;

	public static Connection getConnection() {
		if (!isConnected)
			openConnection();
		return connection;
	}

	private static boolean openConnection() {
		try {
			DriverManager.registerDriver(driver);
			connection = DriverManager.getConnection("jdbc:derby://localhost:3301/doge");
			isConnected = true;
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean closeConnection() {
		try {
			connection.close();
			DriverManager.deregisterDriver(driver);
			isConnected = false;
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
