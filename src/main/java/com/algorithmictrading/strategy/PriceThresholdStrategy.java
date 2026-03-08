package com.algorithmictrading.strategy;

public class PriceThresholdStrategy implements TradingStrategy {
    private double buyThreshold;
    private double sellThreshold;

    public PriceThresholdStrategy(double buyThreshold, double sellThreshold) {
        this.buyThreshold = buyThreshold;
        this.sellThreshold = sellThreshold;
    }

    @Override
    public String determineAction(double currentPrice) {
        if (currentPrice <= buyThreshold) {
            return "BUY";
        } else if (currentPrice >= sellThreshold) {
            return "SELL";
        } else {
            return "HOLD";
        }
    }
}
