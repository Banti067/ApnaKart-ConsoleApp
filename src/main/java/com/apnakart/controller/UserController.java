package com.apnakart.controller;

import java.util.Scanner;

import com.apnakart.model.UsersModel;
import com.apnakart.service.UserService;
import com.apnakart.util.ScannerUtil;

public class UserController {

	private UserService userService = new UserService();
    private Scanner sc = ScannerUtil.getScanner();
    
    public void registerUserController() {
    	
    	 try {
             UsersModel user = new UsersModel();

             System.out.println("\n--- Register to your ApnaKart Account ---");

             System.out.print("Enter Name: ");
             user.setName(sc.nextLine());

             System.out.print("Enter Email: ");
             user.setEmail(sc.nextLine());

             System.out.print("Enter Password: ");
             user.setPassword(sc.nextLine());

             boolean isRegistered = userService.registerService(user);

             if (isRegistered) {
                 System.out.println("Registration successful. Please login.");
             } else {
                 System.out.println("Email already registered. Please login.");
             }

         } catch (Exception e) {
             System.out.println("Error occurred while registering user.");
         }
     }
    
    public UsersModel dologinController() {
    	 try {
             System.out.println("\n---- Login to your ApnaKart Account ----");

             System.out.print("Enter Email: ");
             String email = sc.nextLine();

             System.out.print("Enter Password: ");
             String password = sc.nextLine();

             UsersModel user = userService.loginService(email, password);

             if (user == null) {
                 System.out.println("Account not found. Please register first.");
                 return null;
             }

             if (user.getName() == null) {
                 System.out.println("Incorrect password. Please try again.");
                 return null;
             }

             System.out.println("Login successful. Welcome " + user.getName());
             return user;

         } catch (Exception e) {
             System.out.println("Error occurred while logging in.");
             return null;
         }
     }
}
