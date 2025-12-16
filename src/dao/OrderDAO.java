package dao;

import model.Order;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    // CREATE: Create a new order and return the generated order ID
    public int createOrder() {
        int orderId = -1;
        String query = "INSERT INTO orders (status) VALUES ('PENDING')";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    orderId = rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error: Unable to create new order.");
            e.printStackTrace();
        }

        return orderId;
    }

    // READ: Get order by ID
    public Order getOrderById(int orderId) {
        Order order = null;
        String query = "SELECT * FROM orders WHERE order_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                order = new Order(
                    rs.getInt("order_id"),
                    rs.getTimestamp("order_date"),
                    rs.getDouble("total_amount"),
                    rs.getString("status")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error: Unable to fetch order with ID: " + orderId);
            e.printStackTrace();
        }

        return order;
    }

    // READ: Get all orders
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders ORDER BY order_id DESC";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Order order = new Order(
                    rs.getInt("order_id"),
                    rs.getTimestamp("order_date"),
                    rs.getDouble("total_amount"),
                    rs.getString("status")
                );
                orders.add(order);
            }

        } catch (SQLException e) {
            System.out.println("Error: Unable to fetch orders from database.");
            e.printStackTrace();
        }

        return orders;
    }

    // UPDATE: Update order total amount
    public boolean updateOrderTotal(int orderId, double totalAmount) {
        String query = "UPDATE orders SET total_amount = ? WHERE order_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDouble(1, totalAmount);
            stmt.setInt(2, orderId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Error: Unable to update order total for Order ID: " + orderId);
            e.printStackTrace();
            return false;
        }
    }

    // UPDATE: Update order status
    public boolean updateOrderStatus(int orderId, String status) {
        String query = "UPDATE orders SET status = ? WHERE order_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, status);
            stmt.setInt(2, orderId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Error: Unable to update order status for Order ID: " + orderId);
            e.printStackTrace();
            return false;
        }
    }
}
