package com.apnakart.daodata;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.apnakart.util.ApnaKartDB;

public class ProductData {

	public void doCsvUploadProductData(int batchSize) {

		String sql = "INSERT INTO products (name, price, stock) VALUES (?, ?, ?)";

		int count = 0;

		Connection con = null;

		try {
			con = ApnaKartDB.getConnection();

			con.setAutoCommit(false);

			try (PreparedStatement ps = con.prepareStatement(sql)) {

				InputStream inputStream = getClass().getClassLoader().getResourceAsStream("product_bulk_upload.csv");

				if (inputStream == null) {
					System.out.println("CSV file not found in src/main/resources");
					return;
				}

				BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

				String line;
				boolean header = true;

				while ((line = br.readLine()) != null) {

					if (header) {
						header = false;
						continue;
					}

					String[] data = line.split(",");

					ps.setString(1, data[0].trim());
					ps.setDouble(2, Double.parseDouble(data[1]));
					ps.setInt(3, Integer.parseInt(data[2]));

					ps.addBatch();
					count++;

					if (count % batchSize == 0) {
						ps.executeBatch();
					}
				}

				ps.executeBatch();

				con.commit();

				System.out.println("CSV upload completed successfully.");

			}

		} catch (Exception e) {

			try {
				if (con != null) {
					con.rollback();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			System.out.println("CSV upload failed. Transaction rolled back." + e.getMessage());
//			e.printStackTrace();

		} finally {
			try {
				if (con != null) {
					con.setAutoCommit(true);
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void viewProducts(String name) {

		String selectQuery = "select * from products where name like ?";

		try (Connection con = ApnaKartDB.getConnection(); PreparedStatement ps = con.prepareStatement(selectQuery);) {
			ps.setString(1, "%" + name + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt("product_id")+ " | " +rs.getString("name") + " | " + rs.getBigDecimal("price") + " | " + rs.getInt("stock"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateProduct(String name, int orderQty) {

		String updateQuery = "update products set stock = stock - ? where name = ? and stock >= ?";

		try (Connection con = ApnaKartDB.getConnection(); PreparedStatement ps = con.prepareStatement(updateQuery)) {

			ps.setInt(1, orderQty);
			ps.setString(2, name);
			ps.setInt(3, orderQty);

			int recordUpdate = ps.executeUpdate();

			if (recordUpdate > 0) {
				System.out.println("Stock updated successfully");
			} else {
				System.out.println(" Your Stock or Product not found");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
