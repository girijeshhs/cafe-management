package dao;

import model.Inventory;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventoryDAO {

    // READ: Get all inventory items
    public List<Inventory> getAllInventory() {
        List<Inventory> inventoryList = new ArrayList<>();
        String query = "SELECT * FROM inventory";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Inventory inventory = new Inventory(
                    rs.getInt("inventory_id"),
                    rs.getString("item_name"),
                    rs.getDouble("quantity")
                );
                inventoryList.add(inventory);
            }

        } catch (SQLException e) {
            System.out.println("Error: Unable to fetch inventory from database.");
            e.printStackTrace();
        }

        return inventoryList;
    }

    // READ: Get inventory item by name
    public Inventory getInventoryByName(String itemName) {
        Inventory inventory = null;
        String query = "SELECT * FROM inventory WHERE item_name = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, itemName);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                inventory = new Inventory(
                    rs.getInt("inventory_id"),
                    rs.getString("item_name"),
                    rs.getDouble("quantity")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error: Unable to fetch inventory item: " + itemName);
            e.printStackTrace();
        }

        return inventory;
    }

    // UPDATE: Reduce inventory stock
    public boolean updateInventoryStock(String itemName, double quantityToReduce) {
        String query = "UPDATE inventory SET quantity = quantity - ? WHERE item_name = ? AND quantity >= ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDouble(1, quantityToReduce);
            stmt.setString(2, itemName);
            stmt.setDouble(3, quantityToReduce);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Error: Unable to update inventory for: " + itemName);
            e.printStackTrace();
            return false;
        }
    }

    // UPDATE: Set inventory stock to specific value
    public boolean setInventoryStock(String itemName, double newQuantity) {
        String query = "UPDATE inventory SET quantity = ? WHERE item_name = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDouble(1, newQuantity);
            stmt.setString(2, itemName);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Error: Unable to set inventory for: " + itemName);
            e.printStackTrace();
            return false;
        }
    }
}
