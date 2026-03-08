package com.algorithmictrading.service;

import com.algorithmictrading.model.User;
import com.algorithmictrading.repository.DatabaseManager;
import com.algorithmictrading.strategy.TradingStrategy;

public class TradingBot {
    private User user;
    private MarketDataService marketService;
    private TradingStrategy strategy;
    private String targetCrypto;

    public TradingBot(User user, MarketDataService marketService, TradingStrategy strategy, String targetCrypto) {
        this.user = user;
        this.marketService = marketService;
        this.strategy = strategy;
        this.targetCrypto = targetCrypto;
    }

    public void executeTrade() {
        // 1. Fetch the live price
        double currentPrice = marketService.getCurrentPrice(targetCrypto);
        if (currentPrice == -1) {
            System.out.println("Could not fetch price, trade cancelled.");
            return;
        }

        // 2. Consult the strategy to decide what to do
        String action = strategy.determineAction(currentPrice);
        System.out.println("[" + targetCrypto.toUpperCase() + "] Current Price: $" + currentPrice
                + " -> Algorithm Decision: " + action);

        // 3. Execute the decision (Buy/Sell/Hold)
        if (action.equals("BUY")) {
            if (user.getTotalBalance() >= currentPrice) {
                user.setTotalBalance(user.getTotalBalance() - currentPrice);
                System.out.println("🚀 PURCHASE SUCCESSFUL! Remaining balance: $" + user.getTotalBalance());

                // NEWLY ADDED LINE: Update the database
                DatabaseManager.saveOrUpdateUser(user.getId(), user.getUsername(), user.getTotalBalance());
            } else {
                System.out.println("⚠️ INSUFFICIENT BALANCE! Purchase could not be made.");
            }
        } else if (action.equals("SELL")) {
            user.setTotalBalance(user.getTotalBalance() + currentPrice);
            System.out.println("💰 SALE SUCCESSFUL! New balance: $" + user.getTotalBalance());

            // NEWLY ADDED LINE: Update the database
            DatabaseManager.saveOrUpdateUser(user.getId(), user.getUsername(), user.getTotalBalance());
        } else {
            System.out.println("⏳ Monitoring the market, no trade executed.");
        }
    }
}
