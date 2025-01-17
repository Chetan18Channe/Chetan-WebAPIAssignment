// RewardsServiceTest.java content goes here.
package com.example.rewards.service;

import com.example.rewards.model.Customer;
import com.example.rewards.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RewardsServiceTest {

    private RewardsService rewardsService;

    @BeforeEach
    void setUp() {
        rewardsService = new RewardsService();
    }

    @Test
    void calculateRewards_ShouldCalculateCorrectly() {
        List<Transaction> transactions = List.of(
                new Transaction("C001", "January", 120),
                new Transaction("C001", "February", 80)
        );

        Map<String, Map<String, Integer>> rewards = rewardsService.calculateRewards(transactions);

        assertNotNull(rewards);
        assertEquals(90, rewards.get("C001").get("January"));
        assertEquals(30, rewards.get("C001").get("February"));
    }

    @Test
    void getCustomerRewards_ShouldReturnCorrectRewards() {
        Customer customer = new Customer("C001", "John Doe");
        customer.addTransaction(new Transaction("C001", "January", 120));
        customer.addTransaction(new Transaction("C001", "February", 80));

        rewardsService.addCustomer(customer);

        Map<String, Integer> rewards = rewardsService.getCustomerRewards("C001");

        assertNotNull(rewards);
        assertEquals(90, rewards.get("January"));
        assertEquals(30, rewards.get("February"));
    }

    @Test
    void getAllCustomerRewards_ShouldReturnRewardsForAllCustomers() {
        Customer customer1 = new Customer("C001", "John Doe");
        customer1.addTransaction(new Transaction("C001", "January", 120));
        Customer customer2 = new Customer("C002", "Jane Doe");
        customer2.addTransaction(new Transaction("C002", "February", 150));

        rewardsService.addCustomer(customer1);
        rewardsService.addCustomer(customer2);

        Map<String, Map<String, Integer>> allRewards = rewardsService.getAllCustomerRewards();

        assertNotNull(allRewards);
        assertEquals(90, allRewards.get("C001").get("January"));
        assertEquals(250, allRewards.get("C002").get("February"));
    }
}

