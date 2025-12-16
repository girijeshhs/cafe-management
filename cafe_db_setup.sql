-- Cafe Management System Database Setup
-- Drop database if exists and create fresh
DROP DATABASE IF EXISTS cafe_db;
CREATE DATABASE cafe_db;
USE cafe_db;

-- Table 1: menu_items (stores cafe menu)
CREATE TABLE menu_items (
    item_id INT PRIMARY KEY AUTO_INCREMENT,
    item_name VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) NOT NULL
);

-- Table 2: orders (main order table)
CREATE TABLE orders (
    order_id INT PRIMARY KEY AUTO_INCREMENT,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_amount DECIMAL(10, 2) DEFAULT 0.00,
    status VARCHAR(20) DEFAULT 'PENDING'
);

-- Table 3: order_details (junction table for order items)
CREATE TABLE order_details (
    order_detail_id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT NOT NULL,
    item_id INT NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE,
    FOREIGN KEY (item_id) REFERENCES menu_items(item_id)
);

-- Table 4: inventory (tracks ingredient stock)
CREATE TABLE inventory (
    inventory_id INT PRIMARY KEY AUTO_INCREMENT,
    item_name VARCHAR(100) NOT NULL UNIQUE,
    quantity DECIMAL(10, 2) NOT NULL
);

-- Table 5: payments (payment records)
CREATE TABLE payments (
    payment_id INT PRIMARY KEY AUTO_INCREMENT,
    order_id INT NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    payment_mode VARCHAR(20) NOT NULL,
    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE
);

-- Insert sample menu items
INSERT INTO menu_items (item_name, price) VALUES
('Coffee', 50.00),
('Tea', 30.00),
('Sandwich', 80.00),
('Pastry', 60.00),
('Burger', 120.00),
('Cold Drink', 40.00);

-- Insert sample inventory items
INSERT INTO inventory (item_name, quantity) VALUES
('Milk (Liters)', 100.00),
('Coffee Beans (Kg)', 50.00),
('Tea Leaves (Kg)', 30.00),
('Bread (Pieces)', 200.00),
('Vegetables (Kg)', 80.00),
('Cold Drink Cans', 150.00);

-- Verify setup
SELECT 'Menu Items:' AS Info;
SELECT * FROM menu_items;

SELECT 'Inventory:' AS Info;
SELECT * FROM inventory;

SELECT 'Database setup completed successfully!' AS Status;
