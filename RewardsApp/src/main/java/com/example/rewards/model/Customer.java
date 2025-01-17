package com.example.rewards.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String id;
    private String name;
    private List<Transaction> transactions;

    public Customer(String id, String name) {
        this.id = id;
        this.name = name;
        this.transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    // Getters and setters...
}