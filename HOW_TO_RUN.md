# How to Run - Quick Start Guide

## Step 1: Database Setup

1. **Start MySQL Server**
   - Make sure MySQL is running on your system

2. **Create Database and Tables**
   ```bash
   # Login to MySQL
   mysql -u root -p
   
   # Or run the script directly
   mysql -u root -p < cafe_db_setup.sql
   ```

3. **Verify Setup**
   ```sql
   USE cafe_db;
   SHOW TABLES;
   SELECT * FROM menu_items;
   SELECT * FROM inventory;
   ```

## Step 2: Download MySQL Connector

1. Download from: https://dev.mysql.com/downloads/connector/j/
2. Select "Platform Independent" version
3. Download the ZIP file
4. Extract and locate `mysql-connector-java-X.X.XX.jar`
5. Copy this JAR file to your project folder: `/Users/girijesh/Desktop/Repos/Cafe/`

## Step 3: Configure Database Credentials

Open `src/util/DBUtil.java` and update:

```java
private static final String PASSWORD = "your_mysql_password";
```

## Step 4: Compile the Project

```bash
# Navigate to project directory
cd /Users/girijesh/Desktop/Repos/Cafe

# Create bin directory for compiled classes
mkdir bin

# Compile (macOS/Linux)
javac -cp ".:mysql-connector-java-8.0.33.jar" -d bin src/**/*.java src/*.java

# Compile (Windows)
javac -cp ".;mysql-connector-java-8.0.33.jar" -d bin src/**/*.java src/*.java
```

## Step 5: Run the Application

```bash
# Run (macOS/Linux)
java -cp "bin:mysql-connector-java-8.0.33.jar" Main

# Run (Windows)
java -cp "bin;mysql-connector-java-8.0.33.jar" Main
```

## Step 6: Test the Application

### Sample Test Flow:

1. **Choose Option 1** - View Menu
   - See all available items

2. **Choose Option 2** - Place Order
   - Enter Item ID: 1 (Coffee)
   - Enter Quantity: 2
   - Enter Item ID: 3 (Sandwich)
   - Enter Quantity: 1
   - Enter Item ID: 0 (Finish)
   - Note the Order ID (e.g., 1)

3. **Choose Option 3** - View Order Details
   - Enter Order ID: 1
   - See all items and total

4. **Choose Option 4** - Generate Bill
   - Enter Order ID: 1
   - Bill generated with total amount

5. **Choose Option 5** - Record Payment
   - Enter Order ID: 1
   - Choose Payment Mode: 1 (Cash)
   - Enter Amount: 200
   - Payment recorded, change calculated

6. **Choose Option 6** - View Inventory
   - See updated inventory after order

7. **Choose Option 8** - Exit

## Common Issues and Solutions

### Issue 1: "ClassNotFoundException: com.mysql.cj.jdbc.Driver"
**Solution**: Make sure MySQL Connector JAR is in the classpath
```bash
# Check if JAR file exists in project directory
ls -la mysql-connector-java*.jar
```

### Issue 2: "SQLException: Access denied for user 'root'"
**Solution**: 
1. Update password in `DBUtil.java`
2. Or create a new MySQL user:
   ```sql
   CREATE USER 'cafe_user'@'localhost' IDENTIFIED BY 'cafe123';
   GRANT ALL PRIVILEGES ON cafe_db.* TO 'cafe_user'@'localhost';
   FLUSH PRIVILEGES;
   ```

### Issue 3: "Unknown database 'cafe_db'"
**Solution**: Run the setup script again
```bash
mysql -u root -p < cafe_db_setup.sql
```

### Issue 4: Compilation errors with wildcards
**Solution**: Compile files individually
```bash
# Compile model classes first
javac -cp ".:mysql-connector-java-8.0.33.jar" -d bin src/model/*.java

# Compile util
javac -cp ".:mysql-connector-java-8.0.33.jar" -d bin src/util/*.java

# Compile dao
javac -cp ".:mysql-connector-java-8.0.33.jar:bin" -d bin src/dao/*.java

# Compile service
javac -cp ".:mysql-connector-java-8.0.33.jar:bin" -d bin src/service/*.java

# Compile main
javac -cp ".:mysql-connector-java-8.0.33.jar:bin" -d bin src/Main.java
```

## Using an IDE (IntelliJ IDEA / Eclipse)

### IntelliJ IDEA:
1. Open project folder
2. Right-click on `mysql-connector-java-X.X.XX.jar` → Add as Library
3. Right-click `Main.java` → Run 'Main.main()'

### Eclipse:
1. Import project as "Existing Project"
2. Right-click project → Build Path → Add External Archives
3. Select `mysql-connector-java-X.X.XX.jar`
4. Right-click `Main.java` → Run As → Java Application

## Alternative: Using Maven (Optional)

If you prefer using Maven:

1. Create `pom.xml`:
```xml
<dependencies>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.33</version>
    </dependency>
</dependencies>
```

2. Run:
```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="Main"
```

---

**You're all set! The Cafe Management System is ready to use.**
