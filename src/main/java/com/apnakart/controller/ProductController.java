package com.apnakart.controller;

import java.util.Scanner;
import com.apnakart.service.ProductService;
import com.apnakart.util.ScannerUtil;

public class ProductController {

    private ProductService productService = new ProductService();
    private Scanner scanner = ScannerUtil.getScanner();

    
    public void uploadCSV() {
        System.out.print("Enter batch size: ");
        int batchSize = scanner.nextInt();
        scanner.nextLine();
        productService.uploadCSV(batchSize);
    }

    public void searchProduct() {
        System.out.print("Enter your product name: ");
        String productName = scanner.nextLine();
        productService.searchProduct(productName);
    }
}
