package com.apnakart.daodata;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.apnakart.model.OrderItemsModel;

public class OrdersItemData {
	public void insertOrderItem(Connection con, OrderItemsModel item) throws Exception {

        String sql =
            "INSERT INTO order_items (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, item.getOrderId());
        ps.setInt(2, item.getProductId());
        ps.setInt(3, item.getQuantity());
        ps.setDouble(4, item.getPrice());

        ps.executeUpdate();
    }
}
