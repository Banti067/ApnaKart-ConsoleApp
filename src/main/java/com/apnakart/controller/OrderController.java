package com.apnakart.controller;

import java.util.Scanner;

import com.apnakart.service.OrderService;
import com.apnakart.util.ScannerUtil;

public class OrderController {
	private OrderService orderService = new OrderService();
    private Scanner sc = ScannerUtil.getScanner();

    public void addToCartFromMain() {
        System.out.print("Enter Product ID: ");
        int productId = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Product Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Price: ");
        double price = sc.nextDouble();

        System.out.print("Enter Quantity: ");
        int qty = sc.nextInt();
        sc.nextLine();

        orderService.addToCart(productId, name, price, qty);
    }

    public void viewCartFromMain() {
        orderService.viewCart();
    }

    public void placeOrderFromMain(int userId) {
        orderService.placeOrder(userId);
    }

    public void viewOrderHistoryFromMain(int userId) {
        orderService.viewOrderHistory(userId);
    }
}
