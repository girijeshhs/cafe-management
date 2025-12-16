package service;

import dao.OrderDAO;
import dao.OrderDetailDAO;
import model.Order;

import java.util.Scanner;

public class BillingService {
    private OrderDAO orderDAO;
    private OrderDetailDAO orderDetailDAO;

    public BillingService() {
        this.orderDAO = new OrderDAO();
        this.orderDetailDAO = new OrderDetailDAO();
    }

    // Generate bill for an order
    public void generateBill(Scanner scanner) {
        System.out.println("\n========== GENERATE BILL ==========");

        try {
            System.out.print("Enter Order ID: ");
            int orderId = scanner.nextInt();

            // Check if order exists
            Order order = orderDAO.getOrderById(orderId);
            if (order == null) {
                System.out.println("Order ID not found: " + orderId);
                return;
            }

            // Calculate total from order_details
            double totalAmount = orderDetailDAO.calculateOrderTotal(orderId);

            if (totalAmount == 0) {
                System.out.println("No items found in this order.");
                return;
            }

            // Update order total
            boolean updated = orderDAO.updateOrderTotal(orderId, totalAmount);

            if (updated) {
                System.out.println("\n✓ Bill Generated Successfully!");
                System.out.println("----------------------------------------");
                System.out.println("Order ID: " + orderId);
                System.out.println("Total Amount: ₹" + String.format("%.2f", totalAmount));
                System.out.println("----------------------------------------");
                System.out.println("Status: " + order.getStatus());
            } else {
                System.out.println("Failed to generate bill. Please try again.");
            }

        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid Order ID.");
            scanner.nextLine(); // Clear buffer
        }
    }
}
