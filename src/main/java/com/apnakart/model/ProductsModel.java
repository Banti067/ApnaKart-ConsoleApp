package com.apnakart.model;

public class ProductsModel {

	private int product_Id;
	private String name;
	private double price;
	private int stock;

	public int getProductId() {
		return product_Id;
	}

	public void setProductId(int productId) {
		this.product_Id = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price < 0 ? 0 : price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock < 0 ? 0 : stock;
	}
}
