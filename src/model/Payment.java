package model;

import java.sql.Timestamp;

public class Payment {
    private int paymentId;
    private int orderId;
    private double amount;
    private String paymentMode;
    private Timestamp paymentDate;

    // Default constructor
    public Payment() {
    }

    // Parameterized constructor
    public Payment(int paymentId, int orderId, double amount, String paymentMode, Timestamp paymentDate) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.amount = amount;
        this.paymentMode = paymentMode;
        this.paymentDate = paymentDate;
    }

    // Getters and Setters
    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public Timestamp getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Timestamp paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public String toString() {
        return String.format("Payment ID: %d | Order ID: %d | Amount: ₹%.2f | Mode: %s | Date: %s", 
                           paymentId, orderId, amount, paymentMode, paymentDate);
    }
}
