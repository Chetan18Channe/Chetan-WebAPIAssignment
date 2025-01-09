package com.example.rewards.controller;

import com.example.rewards.model.Transaction;
import com.example.rewards.service.RewardsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rewards")
public class RewardsController {

    private final RewardsService rewardsService;

    public RewardsController(RewardsService rewardsService) {
        this.rewardsService = rewardsService;
    }

    @PostMapping("/calculate")
    public Map<String, Map<String, Integer>> calculateRewards(@RequestBody List<Transaction> transactions) {
        return rewardsService.calculateRewards(transactions);
    }
}
