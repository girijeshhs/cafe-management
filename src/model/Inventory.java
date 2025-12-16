package model;

public class Inventory {
    private int inventoryId;
    private String itemName;
    private double quantity;

    // Default constructor
    public Inventory() {
    }

    // Parameterized constructor
    public Inventory(int inventoryId, String itemName, double quantity) {
        this.inventoryId = inventoryId;
        this.itemName = itemName;
        this.quantity = quantity;
    }

    // Getters and Setters
    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format("ID: %-3d | %-25s | Qty: %.2f", inventoryId, itemName, quantity);
    }
}
