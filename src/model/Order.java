package model;

import java.sql.Timestamp;

public class Order {
    private int orderId;
    private Timestamp orderDate;
    private double totalAmount;
    private String status;

    // Default constructor
    public Order() {
    }

    // Parameterized constructor
    public Order(int orderId, Timestamp orderDate, double totalAmount, String status) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    // Getters and Setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("Order ID: %d | Date: %s | Total: ₹%.2f | Status: %s", 
                           orderId, orderDate, totalAmount, status);
    }
}
