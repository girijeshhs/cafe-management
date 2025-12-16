# Cafe Management System

A console-based Java application for managing cafe operations including menu, orders, billing, payments, and inventory.

## Project Structure

```
Cafe/
├── src/
│   ├── Main.java              # Entry point with console menu
│   ├── model/                 # Entity classes (POJOs)
│   │   ├── MenuItem.java
│   │   ├── Order.java
│   │   ├── OrderDetail.java
│   │   ├── Inventory.java
│   │   └── Payment.java
│   ├── dao/                   # Data Access Objects (CRUD operations)
│   │   ├── MenuItemDAO.java
│   │   ├── OrderDAO.java
│   │   ├── OrderDetailDAO.java
│   │   ├── InventoryDAO.java
│   │   └── PaymentDAO.java
│   ├── service/              # Business logic layer
│   │   ├── MenuService.java
│   │   ├── OrderService.java
│   │   ├── BillingService.java
│   │   ├── PaymentService.java
│   │   └── InventoryService.java
│   └── util/                 # Utility classes
│       └── DBUtil.java       # Database connection handler
└── cafe_db_setup.sql         # Database schema and sample data
```

## Technologies Used

- **Java** (Core Java + JDBC)
- **MySQL** (Database)
- **JDBC** (Java Database Connectivity)
- **SQL** (Structured Query Language)

## Database Schema

The system uses 5 normalized tables:

1. **menu_items** - Stores cafe menu items
2. **orders** - Main order records
3. **order_details** - Junction table for order items (one-to-many)
4. **inventory** - Tracks ingredient stock
5. **payments** - Payment records with different modes

## Setup Instructions

### Prerequisites

- JDK 8 or higher
- MySQL Server 5.7 or higher
- MySQL Connector/J (JDBC Driver)

### Database Setup

1. Start MySQL server
2. Run the SQL script:
   ```bash
   mysql -u root -p < cafe_db_setup.sql
   ```
   Or open MySQL Workbench and execute the `cafe_db_setup.sql` file

### Configuration

1. Update database credentials in `src/util/DBUtil.java`:
   ```java
   private static final String URL = "jdbc:mysql://localhost:3306/cafe_db";
   private static final String USER = "root";
   private static final String PASSWORD = "your_password";  // Change this
   ```

### Download MySQL Connector

1. Download MySQL Connector/J from: https://dev.mysql.com/downloads/connector/j/
2. Extract the JAR file (e.g., `mysql-connector-java-8.0.33.jar`)

### Compilation

Compile the project with MySQL Connector in classpath:

```bash
# Navigate to project directory
cd /Users/girijesh/Desktop/Repos/Cafe

# Compile all Java files
javac -cp ".:mysql-connector-java-8.0.33.jar" -d bin src/**/*.java src/*.java

# If using Windows, use semicolon instead of colon:
javac -cp ".;mysql-connector-java-8.0.33.jar" -d bin src/**/*.java src/*.java
```

### Running the Application

```bash
# Run the application
java -cp "bin:mysql-connector-java-8.0.33.jar" Main

# If using Windows:
java -cp "bin;mysql-connector-java-8.0.33.jar" Main
```

## Features

### 1. View Menu (READ)
- Displays all available menu items with prices
- **CRUD**: READ operation on `menu_items` table

### 2. Place Order (CREATE)
- Create new order
- Add multiple items with quantities
- Automatically updates inventory
- **CRUD**: CREATE operations on `orders` and `order_details` tables
- **CRUD**: UPDATE operation on `inventory` table

### 3. View Order Details (READ)
- Display items in a specific order
- Shows quantity, price, and subtotal
- Uses SQL JOINs between tables
- **CRUD**: READ operation with JOIN on `orders`, `order_details`, `menu_items`

### 4. Generate Bill (UPDATE)
- Calculates total amount from order details
- Updates total in orders table
- **CRUD**: UPDATE operation on `orders` table

### 5. Record Payment (CREATE)
- Records payment with mode (Cash/Card/UPI)
- Calculates change if applicable
- Updates order status to COMPLETED
- **CRUD**: CREATE operation on `payments` table
- **CRUD**: UPDATE operation on `orders` table

### 6. View Inventory (READ)
- Shows current stock levels
- **CRUD**: READ operation on `inventory` table

### 7. Update Inventory (UPDATE)
- Manually update stock quantities
- **CRUD**: UPDATE operation on `inventory` table

## CRUD Coverage

| Operation | Features |
|-----------|----------|
| **CREATE** | Place Order, Record Payment |
| **READ** | View Menu, View Order Details, View Inventory |
| **UPDATE** | Generate Bill, Update Inventory, Auto Inventory Update |
| **DELETE** | Not implemented (intentionally kept simple) |

## Sample Usage Flow

1. **View Menu** → See available items
2. **Place Order** → Add items (Coffee x2, Sandwich x1)
3. **View Order Details** → Check order items
4. **Generate Bill** → Calculate total amount
5. **Record Payment** → Complete payment (Cash/Card/UPI)
6. **View Inventory** → Check remaining stock

## Key Concepts Demonstrated

✅ **JDBC Connection** - Database connectivity using DriverManager  
✅ **PreparedStatement** - SQL injection prevention  
✅ **CRUD Operations** - Full Create, Read, Update operations  
✅ **Database Normalization** - Proper table relationships  
✅ **Foreign Keys** - Referential integrity  
✅ **SQL JOINs** - Multi-table queries  
✅ **Exception Handling** - Try-catch for SQLException  
✅ **OOP Principles** - Encapsulation, separation of concerns  
✅ **Layered Architecture** - Model, DAO, Service separation  
✅ **Business Logic** - Auto inventory update, bill calculation  

## Why This Project is Placement-Ready

1. **Simple & Explainable** - No complex frameworks, pure JDBC
2. **Complete CRUD** - All operations demonstrated
3. **Normalized Database** - Proper schema design
4. **Real-world Flow** - Mimics actual cafe operations
5. **Clean Code Structure** - Organized in layers
6. **Error Handling** - User-friendly error messages
7. **Interview Gold** - Easy to explain in 5-10 minutes

## Future Enhancements

- Order modification (DELETE operation)
- Sales reports and analytics
- User authentication
- Multi-user support with roles
- GUI using Java Swing
- Web interface using Servlets/JSP

## Troubleshooting

**Issue**: `ClassNotFoundException: com.mysql.cj.jdbc.Driver`  
**Solution**: Ensure MySQL Connector JAR is in classpath during compilation and execution

**Issue**: `SQLException: Access denied`  
**Solution**: Update username/password in `DBUtil.java`

**Issue**: `SQLException: Unknown database 'cafe_db'`  
**Solution**: Run the `cafe_db_setup.sql` script first

## Author

Girijesh  
2025
