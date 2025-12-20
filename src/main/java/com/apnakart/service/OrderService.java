package com.apnakart.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;

import com.apnakart.model.CartItemModel;
import com.apnakart.util.ApnaKartDB;

public class OrderService {

	private Map<Integer, CartItemModel> cart = new LinkedHashMap<>();

	public void addToCart(int productId, String name, double price, int qty) {

		if (cart.containsKey(productId)) {
			CartItemModel item = cart.get(productId);
			item.setQuantity(item.getQuantity() + qty);
		} else {
			cart.put(productId, new CartItemModel(productId, name, price, qty));
		}

		System.out.println("Product added to cart......");
	}

	public double viewCart() {

		double total = 0;

		System.out.println("------ CART ------");
		for (CartItemModel item : cart.values()) {
			System.out
					.println(item.getProductName() + " | Qty: " + item.getQuantity() + " | Price: " + item.getPrice());
			total += item.getTotalPrice();
		}

		System.out.println("Total Amount: " + total);
		return total;
	}

	public void placeOrder(int userId) {

		if (cart.isEmpty()) {
			System.out.println("Cart is empty!.");
			return;
		}

		Connection con = null;

		try {
			con = ApnaKartDB.getConnection();
			con.setAutoCommit(false);

			
			String stockCheckSql = "SELECT stock FROM products WHERE product_id = ?";
			PreparedStatement stockCheckPs = con.prepareStatement(stockCheckSql);

			for (CartItemModel item : cart.values()) {

				stockCheckPs.setInt(1, item.getProductId());
				ResultSet rs = stockCheckPs.executeQuery();

				if (!rs.next()) {
					throw new Exception("Product ID not found: " + item.getProductId());
				}

				int availableStock = rs.getInt("stock");

				if (availableStock < item.getQuantity()) {
					throw new Exception("Insufficient stock for product ID " + item.getProductId());
				}
			}

			
			String orderSql = "INSERT INTO orders (user_id, total_amount, order_date) VALUES (?, ?, NOW())";

			PreparedStatement orderPs = con.prepareStatement(orderSql, Statement.RETURN_GENERATED_KEYS);

			double totalAmount = viewCart();

			orderPs.setInt(1, userId);
			orderPs.setDouble(2, totalAmount);
			orderPs.executeUpdate();

			ResultSet rs = orderPs.getGeneratedKeys();
			rs.next();
			int orderId = rs.getInt(1);

			
			String itemSql = "INSERT INTO order_items (order_id, product_id, quantity, price) VALUES (?,?,?,?)";

			String stockUpdateSql = "UPDATE products SET stock = stock - ? WHERE product_id = ?";

			PreparedStatement itemPs = con.prepareStatement(itemSql);
			PreparedStatement stockPs = con.prepareStatement(stockUpdateSql);

			for (CartItemModel item : cart.values()) {

				itemPs.setInt(1, orderId);
				itemPs.setInt(2, item.getProductId());
				itemPs.setInt(3, item.getQuantity());
				itemPs.setDouble(4, item.getPrice());
				itemPs.executeUpdate();

				stockPs.setInt(1, item.getQuantity());
				stockPs.setInt(2, item.getProductId());
				stockPs.executeUpdate();
			}

			con.commit();
			cart.clear();

			System.out.println("Congratulation! Your order placed successfully " + orderId);

		} catch (Exception e) {
			try {
				if (con != null)
					con.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			System.out.println("Order failed: " + e.getMessage());
		}
	}

	public void viewOrderHistory(int userId) {

		try (Connection con = ApnaKartDB.getConnection()) {

			String sql = "SELECT * FROM orders WHERE user_id = ? ORDER BY order_date";

			PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();

			if (!rs.next()) {
				System.out.println("No orders found.");
				return;
			}

			rs.beforeFirst();
			int pageSize = 2;
			int count = 0;

			System.out.println("--- ORDER HISTORY ---");
			while (rs.next()) {

				System.out.println("Order ID: " + rs.getInt("order_id") + " | Amount: " + rs.getDouble("total_amount")
						+ " | Date: " + rs.getDate("order_date"));

				count++;
				if (count % pageSize == 0) {
					System.out.println("Press Enter for next page...");
					System.in.read();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
