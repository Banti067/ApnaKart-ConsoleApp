package com.apnakart.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ApnaKartDB {

	private static final String APNAKART_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String APNAKART_DB_URL = "jdbc:mysql://localhost:3306/apnakart_db";
	private static final String APNAKART_USER = "root";
	private static final String APNAKART_PASSWORD = "Banti@321";

	private static Connection connection = null;
	

	public static Connection getConnection() {
		try {
			Class.forName(APNAKART_DRIVER);
			connection = DriverManager.getConnection(APNAKART_DB_URL, APNAKART_USER, APNAKART_PASSWORD);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Database Connection Error: " + e.getMessage());
		}
		return connection;
	}
}
