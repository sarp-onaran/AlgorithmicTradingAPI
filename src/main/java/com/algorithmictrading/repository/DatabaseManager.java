package com.algorithmictrading.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    // Database file name and location
    private static final String URL = "jdbc:sqlite:trading_bot.db";

    public static void initializeDatabase() {
        // SQL query to create the table
        String createTableSQL = "CREATE TABLE IF NOT EXISTS users ("
                + "id TEXT PRIMARY KEY,"
                + "username TEXT NOT NULL,"
                + "balance REAL NOT NULL"
                + ");";

        try (Connection conn = DriverManager.getConnection(URL);
                Statement stmt = conn.createStatement()) {

            // Execute the SQL command
            stmt.execute(createTableSQL);
            System.out.println("✅ Database and 'users' table loaded successfully.");

        } catch (SQLException e) {
            System.err.println("❌ Database error: " + e.getMessage());
        }
    }

    // Adds the user to the database or updates if they exist
    public static void saveOrUpdateUser(String id, String username, double balance) {
        // If user exists, update balance; if not, create new record (SQLite UPSERT
        // logic)
        String sql = "INSERT INTO users (id, username, balance) VALUES ('" + id + "', '" + username + "', " + balance
                + ") "
                + "ON CONFLICT(id) DO UPDATE SET balance = " + balance + ";";

        try (Connection conn = DriverManager.getConnection(URL);
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("❌ Error saving user: " + e.getMessage());
        }
    }

    // Fetches the current balance from the database
    public static double getUserBalance(String id) {
        String sql = "SELECT balance FROM users WHERE id = '" + id + "';";
        try (Connection conn = DriverManager.getConnection(URL);
                Statement stmt = conn.createStatement();
                var rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getDouble("balance");
            }
        } catch (SQLException e) {
            System.err.println("❌ Error reading balance: " + e.getMessage());
        }
        return -1; // If not found
    }
}
