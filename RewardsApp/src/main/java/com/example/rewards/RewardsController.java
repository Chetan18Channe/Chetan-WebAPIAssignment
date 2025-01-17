package com.example.rewards.controller;

import com.example.rewards.model.Customer;
import com.example.rewards.service.RewardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/rewards/customers")
public class RewardsController {

    @Autowired
    private RewardsService rewardsService;

    @GetMapping("/rewards")
    public ResponseEntity<Map<String, Map<String, Integer>>> getAllCustomerRewards() {
        return ResponseEntity.ok(rewardsService.getAllCustomerRewards());
    }

    @GetMapping("/{customerId}/rewards")
    public ResponseEntity<Map<String, Integer>> getCustomerRewards(@PathVariable String customerId) {
        return ResponseEntity.ok(rewardsService.getCustomerRewards(customerId));
    }

    @PostMapping
    public ResponseEntity<String> addCustomer(@RequestBody Customer customer) {
        rewardsService.addCustomer(customer);
        return ResponseEntity.ok("Customer added successfully!");
    }
}