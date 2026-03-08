package com.algorithmictrading.strategy;

public interface TradingStrategy {
    // Method that evaluates the price and returns "BUY", "SELL", or "HOLD" decision
    String determineAction(double currentPrice);
}
