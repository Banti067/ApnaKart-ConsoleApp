package com.apnakart.model;

public class CartItemModel {
	private int productId;
	private String productName;
	private double price;
	private int quantity;

	public CartItemModel(int productId, String productName, double price, int quantity) {
	        this.productId = productId;
	        this.productName = productName;
	        this.price = price;
	        this.quantity = quantity;
	    }

	public int getProductId() {
		return productId;
	}

	public double getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalPrice() {
		return price * quantity;
	}

	public String getProductName() {
		return productName;
	}
}
