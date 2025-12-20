package com.apnakart.model;

public class OrderItemsModel {

	private int item_Id;
	private int order_Id;
	private int product_Id;
	private int quantity;
	private double price;

	public int getItemId() {
		return item_Id;
	}

	public void setItemId(int itemId) {
		this.item_Id = itemId;
	}

	public int getOrderId() {
		return order_Id;
	}

	public void setOrderId(int orderId) {
		this.order_Id = orderId;
	}

	public int getProductId() {
		return product_Id;
	}

	public void setProductId(int productId) {
		this.product_Id = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public OrderItemsModel() {}

    public OrderItemsModel(int orderId, int productId, int quantity, double price) {
        this.order_Id = orderId;
        this.product_Id = productId;
        this.quantity = quantity;
        this.price = price;
    }
}
