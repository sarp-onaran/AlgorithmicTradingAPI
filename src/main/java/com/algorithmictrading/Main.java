package com.algorithmictrading;

import com.algorithmictrading.model.User;
import com.algorithmictrading.service.MarketDataService;
import com.algorithmictrading.service.TradingBot;
import com.algorithmictrading.strategy.PriceThresholdStrategy;
import com.algorithmictrading.strategy.TradingStrategy;
import com.algorithmictrading.repository.DatabaseManager;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Starting Algorithmic Trading API ---\n");

        // 1. Initialize the database
        DatabaseManager.initializeDatabase();

        // 2. Try to fetch the user from the database
        String userId = "101";
        double savedBalance = DatabaseManager.getUserBalance(userId);

        User sarp;
        if (savedBalance == -1) {
            // First time login - start with $100,000 and save to database
            sarp = new User(userId, "sarponaran", 100000.0);
            DatabaseManager.saveOrUpdateUser(sarp.getId(), sarp.getUsername(), sarp.getTotalBalance());
            System.out.println("New account created.");
        } else {
            // If exists, load balance from database
            sarp = new User(userId, "sarponaran", savedBalance);
            System.out.println("Account loaded from database.");
        }

        System.out.println("Client: " + sarp.getUsername() + " | Current Balance: $" + sarp.getTotalBalance());

        // 3. Set up Services and Strategy
        MarketDataService marketService = new MarketDataService();

        // WARNING: Threshold set very high (100,000) to force an immediate BUY action!
        TradingStrategy strategy = new PriceThresholdStrategy(100000.0, 150000.0);
        TradingBot bot = new TradingBot(sarp, marketService, strategy, "bitcoin");

        // 4. Trigger the Trade Engine
        System.out.println("\n--- Triggering Trade Engine ---");
        bot.executeTrade();
    }
}
