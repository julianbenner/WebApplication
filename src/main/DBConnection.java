package main;

import org.apache.derby.jdbc.EmbeddedDriver;

import java.sql.*;
/**
 * Created by Julian on 09/02/14.
 */
public class DBConnection {
    private static Connection connection;
    private static Driver driver = new EmbeddedDriver();
	private static String username = "";
	private static String password = "";
	private static boolean isConnected = false;

	public static Connection getConnection() {
		if(!isConnected)
			openConnection();
		return connection;
	}

	public static boolean openConnection()
	{
		try {
			DriverManager.registerDriver(driver);
			connection = DriverManager.getConnection("jdbc:derby://localhost:3301/myDatabase");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean closeConnection()
	{
		try {
			connection.close();
			DriverManager.deregisterDriver(driver);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
