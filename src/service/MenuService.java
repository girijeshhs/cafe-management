package service;

import dao.MenuItemDAO;
import model.MenuItem;

import java.util.List;

public class MenuService {
    private MenuItemDAO menuItemDAO;

    public MenuService() {
        this.menuItemDAO = new MenuItemDAO();
    }

    // View all menu items
    public void viewMenu() {
        System.out.println("\n========== CAFE MENU ==========");

        List<MenuItem> menuItems = menuItemDAO.getAllMenuItems();

        if (menuItems.isEmpty()) {
            System.out.println("No menu items available.");
            return;
        }

        System.out.println("\n--- Available Items ---");
        for (MenuItem item : menuItems) {
            System.out.println(item);
        }
    }
}
