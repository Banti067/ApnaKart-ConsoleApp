package com.apnakart.service;

import com.apnakart.daodata.ProductData;

public class ProductService {
	private ProductData productData = new ProductData();

	public void uploadCSV(int batchSize) {
		productData.doCsvUploadProductData(batchSize);
	}

	public void searchProduct(String productName) {
		if (productName == null || productName.isEmpty()) {
			System.out.println("Check! Product name cannot be empty");
			return;
		}
		productData.viewProducts(productName);
	}

	public void reduceStockAfterOrder(String productName, int quantity) {
		if (quantity <= 0) {
			System.out.println("Put valid order quantity");
			return;
		}
		productData.updateProduct(productName, quantity);
	}
}
