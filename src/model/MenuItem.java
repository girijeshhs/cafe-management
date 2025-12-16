package model;

public class MenuItem {
    private int itemId;
    private String itemName;
    private double price;

    // Default constructor
    public MenuItem() {
    }

    // Parameterized constructor
    public MenuItem(int itemId, String itemName, double price) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.price = price;
    }

    // Getters and Setters
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("ID: %-3d | %-20s | ₹%.2f", itemId, itemName, price);
    }
}
