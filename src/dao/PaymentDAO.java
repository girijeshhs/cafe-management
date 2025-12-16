package dao;

import model.Payment;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {

    // CREATE: Record a new payment
    public boolean recordPayment(int orderId, double amount, String paymentMode) {
        String query = "INSERT INTO payments (order_id, amount, payment_mode) VALUES (?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, orderId);
            stmt.setDouble(2, amount);
            stmt.setString(3, paymentMode);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Error: Unable to record payment.");
            e.printStackTrace();
            return false;
        }
    }

    // READ: Get payment by order ID
    public Payment getPaymentByOrderId(int orderId) {
        Payment payment = null;
        String query = "SELECT * FROM payments WHERE order_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                payment = new Payment(
                    rs.getInt("payment_id"),
                    rs.getInt("order_id"),
                    rs.getDouble("amount"),
                    rs.getString("payment_mode"),
                    rs.getTimestamp("payment_date")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error: Unable to fetch payment for Order ID: " + orderId);
            e.printStackTrace();
        }

        return payment;
    }

    // READ: Get all payments
    public List<Payment> getAllPayments() {
        List<Payment> payments = new ArrayList<>();
        String query = "SELECT * FROM payments ORDER BY payment_id DESC";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Payment payment = new Payment(
                    rs.getInt("payment_id"),
                    rs.getInt("order_id"),
                    rs.getDouble("amount"),
                    rs.getString("payment_mode"),
                    rs.getTimestamp("payment_date")
                );
                payments.add(payment);
            }

        } catch (SQLException e) {
            System.out.println("Error: Unable to fetch payments from database.");
            e.printStackTrace();
        }

        return payments;
    }
}
