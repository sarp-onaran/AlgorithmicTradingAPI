package com.algorithmictrading.model;

public class User {
    private String id;
    private String username;
    private double totalBalance; // Cash dollar balance in the system

    public User(String id, String username, double initialBalance) {
        this.id = id;
        this.username = username;
        this.totalBalance = initialBalance;
    }

    // Getter and Setter methods
    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public double getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(double totalBalance) {
        this.totalBalance = totalBalance;
    }
}
