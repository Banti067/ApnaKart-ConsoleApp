package com.apnakart.daodata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.apnakart.model.UsersModel;
import com.apnakart.util.ApnaKartDB;

public class UserData {

	// USer Register Credential
	public boolean userRegisterDAO(UsersModel user) {

		String checksqlQuery = "select email from users where email = ? ";
		String insertsqlQuery = "insert into users(name,email,password) values(?,?,?)";

		try (Connection con = ApnaKartDB.getConnection(); PreparedStatement ps = con.prepareStatement(checksqlQuery)) {

			ps.setString(1, user.getEmail());
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				// System.out.println("Email already Registered.");
				return false;
			}

			try (PreparedStatement ps1 = con.prepareStatement(insertsqlQuery)) {
				ps1.setString(1, user.getName());
				ps1.setString(2, user.getEmail());
				ps1.setString(3, user.getPassword());

				int result = ps1.executeUpdate();
				if (result > 0) {
					// System.out.println("User registered successfully.");
					return true;
				}
			}

		} catch (SQLException e) {
			System.out.println("Registration failed: " + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	// Login Credential
	public UsersModel doLoginDAO(String email, String password) {

		String loginSqlQuery = "SELECT user_id, name, password FROM users WHERE email = ?";

		try (Connection con = ApnaKartDB.getConnection(); PreparedStatement ps = con.prepareStatement(loginSqlQuery)) {

			ps.setString(1, email);

			ResultSet rs = ps.executeQuery();

			if (!rs.next()) {
				// System.out.println("Email is not registered.");
				return null;
			}

			if (!rs.getString("password").equals(password)) {
				return new UsersModel();
			}
			UsersModel user = new UsersModel();
			user.setUserId(rs.getInt("user_id"));
			user.setName(rs.getString("name"));
			user.setEmail(email);

			return user;

		} catch (SQLException e) {
			System.out.println("Login failed: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

}
