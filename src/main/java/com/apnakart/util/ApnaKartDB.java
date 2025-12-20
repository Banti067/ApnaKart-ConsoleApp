package com.apnakart.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ApnaKartDB {

	private static final String apnakart_Driver = "com.mysql.cj.jdbc.Driver";
	private static final String apnakart_DB_URL = "jdbc:mysql://localhost:3306/apnakart_db";
	private static final String apnakart_USER = "root";
	private static final String apnakart_PASSWORD = "Banti@321";

	private static Connection connection = null;
	

	public static Connection getConnection() {
		try {
			Class.forName(apnakart_Driver);
			connection = DriverManager.getConnection(apnakart_DB_URL, apnakart_USER, apnakart_PASSWORD);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Database Connection Error: " + e.getMessage());
		}
		return connection;
	}
}
