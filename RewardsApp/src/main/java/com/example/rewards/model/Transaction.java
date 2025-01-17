package com.example.rewards.model;

public class Transaction {
    private String customerId;
    private String month;
    private double amount;

    public Transaction(String customerId, String month, double amount) {
        this.customerId = customerId;
        this.month = month;
        this.amount = amount;
    }

    // Getters and setters...
}