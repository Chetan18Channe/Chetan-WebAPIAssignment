package com.example.rewards.service;

import com.example.rewards.model.Customer;
import com.example.rewards.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RewardsService {

    private final Map<String, Customer> customers = new HashMap<>();

    public Map<String, Map<String, Integer>> getAllCustomerRewards() {
        Map<String, Map<String, Integer>> rewards = new HashMap<>();
        for (Customer customer : customers.values()) {
            rewards.put(customer.getId(), calculateRewardsForCustomer(customer));
        }
        return rewards;
    }

    public Map<String, Integer> getCustomerRewards(String customerId) {
        Customer customer = customers.get(customerId);
        if (customer == null) return new HashMap<>();
        return calculateRewardsForCustomer(customer);
    }

    public void addCustomer(Customer customer) {
        customers.put(customer.getId(), customer);
    }

    private Map<String, Integer> calculateRewardsForCustomer(Customer customer) {
        Map<String, Integer> rewards = new HashMap<>();
        for (Transaction transaction : customer.getTransactions()) {
            int points = calculatePoints(transaction.getAmount());
            rewards.merge(transaction.getMonth(), points, Integer::sum);
        }
        return rewards;
    }

    private int calculatePoints(double amount) {
        int points = 0;
        if (amount > 100) {
            points += (amount - 100) * 2;
            amount = 100;
        }
        if (amount > 50) {
            points += (amount - 50);
        }
        return points;
    }
}