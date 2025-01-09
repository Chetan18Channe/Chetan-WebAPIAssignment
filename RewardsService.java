package com.example.rewards.service;

import com.example.rewards.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RewardsService {

    public Map<String, Map<String, Integer>> calculateRewards(List<Transaction> transactions) {
        Map<String, Map<String, Integer>> customerRewards = new HashMap<>();

        for (Transaction transaction : transactions) {
            String customerId = transaction.getCustomerId();
            String month = transaction.getMonth();
            double amount = transaction.getAmount();

            int points = calculatePoints(amount);

            customerRewards.computeIfAbsent(customerId, k -> new HashMap<>())
                           .merge(month, points, Integer::sum);
        }

        return customerRewards;
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
