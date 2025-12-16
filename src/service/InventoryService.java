package service;

import dao.InventoryDAO;
import model.Inventory;

import java.util.List;
import java.util.Scanner;

public class InventoryService {
    private InventoryDAO inventoryDAO;

    public InventoryService() {
        this.inventoryDAO = new InventoryDAO();
    }

    // View all inventory
    public void viewInventory() {
        System.out.println("\n========== INVENTORY ==========");

        List<Inventory> inventoryList = inventoryDAO.getAllInventory();

        if (inventoryList.isEmpty()) {
            System.out.println("No inventory items found.");
            return;
        }

        System.out.println("\n--- Current Inventory ---");
        for (Inventory item : inventoryList) {
            System.out.println(item);
        }
    }

    // Manual inventory update
    public void updateInventory(Scanner scanner) {
        System.out.println("\n========== UPDATE INVENTORY ==========");

        // Show current inventory
        viewInventory();

        try {
            System.out.print("\nEnter Inventory ID to update: ");
            int inventoryId = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            System.out.print("Enter new quantity: ");
            double newQuantity = scanner.nextDouble();

            if (newQuantity < 0) {
                System.out.println("Quantity cannot be negative.");
                return;
            }

            // Get inventory item by ID
            List<Inventory> inventoryList = inventoryDAO.getAllInventory();
            Inventory selectedItem = null;

            for (Inventory item : inventoryList) {
                if (item.getInventoryId() == inventoryId) {
                    selectedItem = item;
                    break;
                }
            }

            if (selectedItem == null) {
                System.out.println("Inventory ID not found.");
                return;
            }

            // Update stock
            boolean updated = inventoryDAO.setInventoryStock(selectedItem.getItemName(), newQuantity);

            if (updated) {
                System.out.println("\n✓ Inventory Updated Successfully!");
                System.out.println(selectedItem.getItemName() + " - New Quantity: " + newQuantity);
            } else {
                System.out.println("Failed to update inventory.");
            }

        } catch (Exception e) {
            System.out.println("Invalid input. Please enter valid values.");
            scanner.nextLine(); // Clear buffer
        }
    }
}
