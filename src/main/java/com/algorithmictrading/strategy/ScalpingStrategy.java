package com.algorithmictrading.strategy;

/**
 * Scalping Strategy: Aims to profit from short-term, small price movements.
 * Buys on percentage drops and sells on percentage rises relative to a base price.
 */
public class ScalpingStrategy implements TradingStrategy {
    private double basePrice;
    private double buyDropPercentage;
    private double sellRisePercentage;

    public ScalpingStrategy(double basePrice, double buyDropPercentage, double sellRisePercentage) {
        this.basePrice = basePrice;
        this.buyDropPercentage = buyDropPercentage; // e.g., buy if price drops 1%
        this.sellRisePercentage = sellRisePercentage; // e.g., sell if price rises 1.5%
    }

    @Override
    public String determineAction(double currentPrice) {
        double buyTarget = basePrice * (1 - (buyDropPercentage / 100));
        double sellTarget = basePrice * (1 + (sellRisePercentage / 100));

        if (currentPrice <= buyTarget) {
            return "BUY";
        } else if (currentPrice >= sellTarget) {
            return "SELL";
        } else {
            return "HOLD";
        }
    }
}
