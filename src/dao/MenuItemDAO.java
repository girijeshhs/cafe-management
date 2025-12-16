package dao;

import model.MenuItem;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuItemDAO {

    // READ: Get all menu items
    public List<MenuItem> getAllMenuItems() {
        List<MenuItem> menuItems = new ArrayList<>();
        String query = "SELECT * FROM menu_items";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                MenuItem item = new MenuItem(
                    rs.getInt("item_id"),
                    rs.getString("item_name"),
                    rs.getDouble("price")
                );
                menuItems.add(item);
            }

        } catch (SQLException e) {
            System.out.println("Error: Unable to fetch menu items from database.");
            e.printStackTrace();
        }

        return menuItems;
    }

    // READ: Get menu item by ID
    public MenuItem getMenuItemById(int itemId) {
        MenuItem menuItem = null;
        String query = "SELECT * FROM menu_items WHERE item_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, itemId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                menuItem = new MenuItem(
                    rs.getInt("item_id"),
                    rs.getString("item_name"),
                    rs.getDouble("price")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error: Unable to fetch menu item with ID: " + itemId);
            e.printStackTrace();
        }

        return menuItem;
    }
}
