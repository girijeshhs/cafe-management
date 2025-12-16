package service;

import dao.MenuItemDAO;
import dao.OrderDAO;
import dao.OrderDetailDAO;
import dao.InventoryDAO;
import model.MenuItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class OrderService {
    private MenuItemDAO menuItemDAO;
    private OrderDAO orderDAO;
    private OrderDetailDAO orderDetailDAO;
    private InventoryDAO inventoryDAO;

    public OrderService() {
        this.menuItemDAO = new MenuItemDAO();
        this.orderDAO = new OrderDAO();
        this.orderDetailDAO = new OrderDetailDAO();
        this.inventoryDAO = new InventoryDAO();
    }

    // Place a new order
    public void placeOrder(Scanner scanner) {
        System.out.println("\n========== PLACE ORDER ==========");

        // Step 1: Create order
        int orderId = orderDAO.createOrder();
        if (orderId == -1) {
            System.out.println("Failed to create order. Please try again.");
            return;
        }

        System.out.println("New Order Created! Order ID: " + orderId);
        
        // Step 2: Display menu
        List<MenuItem> menuItems = menuItemDAO.getAllMenuItems();
        if (menuItems.isEmpty()) {
            System.out.println("No menu items available.");
            return;
        }

        System.out.println("\n--- MENU ---");
        for (MenuItem item : menuItems) {
            System.out.println(item);
        }

        // Step 3: Add items to order
        Map<Integer, Integer> itemQuantities = new HashMap<>();
        boolean addingItems = true;

        while (addingItems) {
            try {
                System.out.print("\nEnter Item ID (0 to finish): ");
                int itemId = scanner.nextInt();

                if (itemId == 0) {
                    addingItems = false;
                    continue;
                }

                // Validate item exists
                MenuItem selectedItem = menuItemDAO.getMenuItemById(itemId);
                if (selectedItem == null) {
                    System.out.println("Invalid Item ID. Please try again.");
                    continue;
                }

                System.out.print("Enter Quantity: ");
                int quantity = scanner.nextInt();

                if (quantity <= 0) {
                    System.out.println("Quantity must be greater than 0.");
                    continue;
                }

                // Add to order details
                boolean added = orderDetailDAO.addOrderDetail(orderId, itemId, quantity, selectedItem.getPrice());
                if (added) {
                    itemQuantities.put(itemId, itemQuantities.getOrDefault(itemId, 0) + quantity);
                    System.out.println("Added: " + selectedItem.getItemName() + " x" + quantity);
                } else {
                    System.out.println("Failed to add item to order.");
                }

            } catch (Exception e) {
                System.out.println("Invalid input. Please enter numbers only.");
                scanner.nextLine(); // Clear buffer
            }
        }

        if (itemQuantities.isEmpty()) {
            System.out.println("No items added to order. Order cancelled.");
            return;
        }

        // Step 4: Automatically update inventory
        System.out.println("\nUpdating inventory...");
        updateInventoryForOrder(itemQuantities);

        System.out.println("\n✓ Order placed successfully! Order ID: " + orderId);
        System.out.println("Use 'Generate Bill' option to calculate the total amount.");
    }

    // Automatically update inventory after order
    private void updateInventoryForOrder(Map<Integer, Integer> itemQuantities) {
        // Simple mapping: reduce inventory based on items ordered
        // In real scenario, you'd have a proper mapping table
        for (Map.Entry<Integer, Integer> entry : itemQuantities.entrySet()) {
            int itemId = entry.getKey();
            int quantity = entry.getValue();

            // Example inventory reduction logic
            // This is simplified - you can enhance based on requirements
            switch (itemId) {
                case 1: // Coffee
                    inventoryDAO.updateInventoryStock("Coffee Beans (Kg)", quantity * 0.05);
                    inventoryDAO.updateInventoryStock("Milk (Liters)", quantity * 0.2);
                    break;
                case 2: // Tea
                    inventoryDAO.updateInventoryStock("Tea Leaves (Kg)", quantity * 0.02);
                    inventoryDAO.updateInventoryStock("Milk (Liters)", quantity * 0.1);
                    break;
                case 3: // Sandwich
                    inventoryDAO.updateInventoryStock("Bread (Pieces)", quantity * 2);
                    inventoryDAO.updateInventoryStock("Vegetables (Kg)", quantity * 0.1);
                    break;
                case 6: // Cold Drink
                    inventoryDAO.updateInventoryStock("Cold Drink Cans", quantity);
                    break;
            }
        }
        System.out.println("Inventory updated successfully.");
    }

    // View order details
    public void viewOrderDetails(Scanner scanner) {
        System.out.println("\n========== VIEW ORDER DETAILS ==========");

        try {
            System.out.print("Enter Order ID: ");
            int orderId = scanner.nextInt();

            // Get order details
            List<String> orderDetails = orderDetailDAO.getOrderDetailsByOrderId(orderId);

            if (orderDetails.isEmpty()) {
                System.out.println("No details found for Order ID: " + orderId);
                return;
            }

            System.out.println("\n--- Order Details for Order ID: " + orderId + " ---");
            for (String detail : orderDetails) {
                System.out.println(detail);
            }

            // Display total
            double total = orderDetailDAO.calculateOrderTotal(orderId);
            System.out.println("----------------------------------------");
            System.out.println("Total Amount: ₹" + String.format("%.2f", total));

        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid Order ID.");
            scanner.nextLine(); // Clear buffer
        }
    }
}
