package com.algorithmictrading.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TradeLogger {
    private static final String FILE_NAME = "trade_history.txt";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void logAction(String crypto, String action, double price, double balance) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true);
                PrintWriter pw = new PrintWriter(fw)) {

            String timestamp = LocalDateTime.now().format(formatter);
            String logEntry = String.format("[%s] %s | %s | Price: $%.2f | New Balance: $%.2f",
                    timestamp, crypto.toUpperCase(), action, price, balance);

            pw.println(logEntry);
            System.out.println("📝 Trade successfully logged to file.");

        } catch (IOException e) {
            System.err.println("❌ Error writing log: " + e.getMessage());
        }
    }
}
