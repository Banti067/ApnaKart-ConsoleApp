package com.apnakart.main;

import java.util.Scanner;

import com.apnakart.controller.OrderController;
import com.apnakart.controller.ProductController;
import com.apnakart.controller.UserController;
import com.apnakart.model.UsersModel;
import com.apnakart.util.ScannerUtil;

public class ApnaKartMain {

    public static void main(String[] args) {

        Scanner scanner = ScannerUtil.getScanner();

        UserController userController = new UserController();
        ProductController productController = new ProductController();
        OrderController orderController = new OrderController();

        UsersModel loggedInUser = null;
        boolean exitApp = false;

        // -------- BEFORE LOGIN --------
        while (!exitApp) {

            while (loggedInUser == null) {

                System.out.println("\n------- ApnaKart -------");
                System.out.println("1. Register User");
                System.out.println("2. Login User");
                System.out.println("3. Logout");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                case 1:
                    userController.registerUserController();
                    break;

                case 2:
                    loggedInUser = userController.dologinController();
                    if (loggedInUser == null || loggedInUser.getUserId() == 0) {
                        loggedInUser = null;
                        System.out.println("Invalid credentials.");
                    }
                    break;

                case 3:
                    System.out.println("Thank you for using ApnaKart.");
                    exitApp = true;
                    break;

                default:
                    System.out.println("Invalid choice.");
                }
            }

            if (exitApp) break;

            // -------- AFTER LOGIN --------
            System.out.println("\nWelcome, " + loggedInUser.getName());
            boolean logout = false;

            while (!logout) {

                System.out.println("\n----- MAIN MENU -----");
                System.out.println("1. Upload Products from CSV");
                System.out.println("2. Search / View Products");
                System.out.println("3. Add Product to Cart");
                System.out.println("4. View Cart");
                System.out.println("5. Place Order");
                System.out.println("6. View Order History");
                System.out.println("7. Logout");
                System.out.print("Enter your choice: ");

                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                case 1:
                    productController.uploadCSV();
                    break;

                case 2:
                    productController.searchProduct();
                    break;

                case 3:
                    orderController.addToCartFromMain();
                    break;

                case 4:
                    orderController.viewCartFromMain();
                    break;

                case 5:
                    orderController.placeOrderFromMain(loggedInUser.getUserId());
                    break;

                case 6:
                    orderController.viewOrderHistoryFromMain(loggedInUser.getUserId());
                    break;

                case 7:
                    loggedInUser = null;
                    logout = true;
                    System.out.println("Logged out successfully.");
                    break;

                default:
                    System.out.println("Invalid option.");
                }
            }
        }
    }
}
