package com.apnakart.model;

import java.sql.Timestamp;

public class OrdersModel {

	private int order_Id;
	private int user_Id;
	private double totalAmount;
	private Timestamp orderDate;

	public int getOrderId() {
		return order_Id;
	}

	public void setOrderId(int orderId) {
		this.order_Id = orderId;
	}

	public int getUserId() {
		return user_Id;
	}

	public void setUserId(int userId) {
		this.user_Id = userId;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}
}
