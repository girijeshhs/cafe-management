package service;

import dao.PaymentDAO;
import dao.OrderDAO;
import model.Order;

import java.util.Scanner;

public class PaymentService {
    private PaymentDAO paymentDAO;
    private OrderDAO orderDAO;

    public PaymentService() {
        this.paymentDAO = new PaymentDAO();
        this.orderDAO = new OrderDAO();
    }

    // Record payment for an order
    public void recordPayment(Scanner scanner) {
        System.out.println("\n========== RECORD PAYMENT ==========");

        try {
            System.out.print("Enter Order ID: ");
            int orderId = scanner.nextInt();

            // Check if order exists
            Order order = orderDAO.getOrderById(orderId);
            if (order == null) {
                System.out.println("Order ID not found: " + orderId);
                return;
            }

            // Display order total
            System.out.println("Order Total: ₹" + String.format("%.2f", order.getTotalAmount()));

            if (order.getTotalAmount() == 0) {
                System.out.println("Please generate bill first before recording payment.");
                return;
            }

            // Get payment mode
            System.out.println("\nSelect Payment Mode:");
            System.out.println("1. Cash");
            System.out.println("2. Card");
            System.out.println("3. UPI");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();

            String paymentMode;
            switch (choice) {
                case 1:
                    paymentMode = "CASH";
                    break;
                case 2:
                    paymentMode = "CARD";
                    break;
                case 3:
                    paymentMode = "UPI";
                    break;
                default:
                    System.out.println("Invalid payment mode.");
                    return;
            }

            System.out.print("Enter Amount Paid: ₹");
            double amount = scanner.nextDouble();

            if (amount < order.getTotalAmount()) {
                System.out.println("Error: Payment amount is less than order total.");
                return;
            }

            // Record payment
            boolean recorded = paymentDAO.recordPayment(orderId, amount, paymentMode);

            if (recorded) {
                // Update order status to COMPLETED
                orderDAO.updateOrderStatus(orderId, "COMPLETED");

                System.out.println("\n✓ Payment Recorded Successfully!");
                System.out.println("----------------------------------------");
                System.out.println("Order ID: " + orderId);
                System.out.println("Amount Paid: ₹" + String.format("%.2f", amount));
                System.out.println("Payment Mode: " + paymentMode);
                
                if (amount > order.getTotalAmount()) {
                    double change = amount - order.getTotalAmount();
                    System.out.println("Change to Return: ₹" + String.format("%.2f", change));
                }
                
                System.out.println("Order Status: COMPLETED");
                System.out.println("----------------------------------------");
            } else {
                System.out.println("Failed to record payment. Please try again.");
            }

        } catch (Exception e) {
            System.out.println("Invalid input. Please enter valid values.");
            scanner.nextLine(); // Clear buffer
        }
    }
}
