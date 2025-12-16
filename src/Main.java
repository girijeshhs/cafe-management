import service.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Initialize services
        MenuService menuService = new MenuService();
        OrderService orderService = new OrderService();
        BillingService billingService = new BillingService();
        PaymentService paymentService = new PaymentService();
        InventoryService inventoryService = new InventoryService();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=======================================");
        System.out.println("   CAFE MANAGEMENT SYSTEM");
        System.out.println("=======================================");

        while (running) {
            try {
                displayMainMenu();
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Clear buffer

                switch (choice) {
                    case 1:
                        menuService.viewMenu();
                        break;

                    case 2:
                        orderService.placeOrder(scanner);
                        break;

                    case 3:
                        orderService.viewOrderDetails(scanner);
                        break;

                    case 4:
                        billingService.generateBill(scanner);
                        break;

                    case 5:
                        paymentService.recordPayment(scanner);
                        break;

                    case 6:
                        inventoryService.viewInventory();
                        break;

                    case 7:
                        inventoryService.updateInventory(scanner);
                        break;

                    case 8:
                        System.out.println("\nThank you for using Cafe Management System!");
                        System.out.println("Goodbye!");
                        running = false;
                        break;

                    default:
                        System.out.println("\nInvalid choice. Please select between 1-8.");
                }

                if (running) {
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                }

            } catch (Exception e) {
                System.out.println("\nError: Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear buffer
            }
        }

        scanner.close();
    }

    private static void displayMainMenu() {
        System.out.println("\n=======================================");
        System.out.println("           MAIN MENU");
        System.out.println("=======================================");
        System.out.println("1. View Menu");
        System.out.println("2. Place Order");
        System.out.println("3. View Order Details");
        System.out.println("4. Generate Bill");
        System.out.println("5. Record Payment");
        System.out.println("6. View Inventory");
        System.out.println("7. Update Inventory");
        System.out.println("8. Exit");
        System.out.println("=======================================");
    }
}
