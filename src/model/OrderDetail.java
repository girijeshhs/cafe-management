package model;

public class OrderDetail {
    private int orderDetailId;
    private int orderId;
    private int itemId;
    private int quantity;
    private double price;

    // Default constructor
    public OrderDetail() {
    }

    // Parameterized constructor
    public OrderDetail(int orderDetailId, int orderId, int itemId, int quantity, double price) {
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and Setters
    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Detail ID: %d | Order ID: %d | Item ID: %d | Qty: %d | Price: ₹%.2f", 
                           orderDetailId, orderId, itemId, quantity, price);
    }
}
