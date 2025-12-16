package dao;

import model.OrderDetail;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAO {

    // CREATE: Add order detail
    public boolean addOrderDetail(int orderId, int itemId, int quantity, double price) {
        String query = "INSERT INTO order_details (order_id, item_id, quantity, price) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, orderId);
            stmt.setInt(2, itemId);
            stmt.setInt(3, quantity);
            stmt.setDouble(4, price);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Error: Unable to add order detail.");
            e.printStackTrace();
            return false;
        }
    }

    // READ: Get order details by order ID (with JOIN to get item names)
    public List<String> getOrderDetailsByOrderId(int orderId) {
        List<String> orderDetails = new ArrayList<>();
        String query = "SELECT od.quantity, od.price, mi.item_name " +
                      "FROM order_details od " +
                      "JOIN menu_items mi ON od.item_id = mi.item_id " +
                      "WHERE od.order_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String detail = String.format("%-20s | Qty: %-3d | Price: ₹%-8.2f | Subtotal: ₹%.2f",
                    rs.getString("item_name"),
                    rs.getInt("quantity"),
                    rs.getDouble("price"),
                    rs.getInt("quantity") * rs.getDouble("price")
                );
                orderDetails.add(detail);
            }

        } catch (SQLException e) {
            System.out.println("Error: Unable to fetch order details for Order ID: " + orderId);
            e.printStackTrace();
        }

        return orderDetails;
    }

    // READ: Calculate total for an order
    public double calculateOrderTotal(int orderId) {
        double total = 0.0;
        String query = "SELECT SUM(quantity * price) AS total FROM order_details WHERE order_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                total = rs.getDouble("total");
            }

        } catch (SQLException e) {
            System.out.println("Error: Unable to calculate order total for Order ID: " + orderId);
            e.printStackTrace();
        }

        return total;
    }
}
