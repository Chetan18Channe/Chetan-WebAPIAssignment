// Test content for RewardsControllerTest.
package com.example.rewards.controller;

import com.example.rewards.model.Customer;
import com.example.rewards.model.Transaction;
import com.example.rewards.service.RewardsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RewardsController.class)
class RewardsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RewardsService rewardsService;

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer("C001", "John Doe");
    }

    @Test
    void getAllCustomerRewards_ShouldReturnRewards() throws Exception {
        when(rewardsService.getAllCustomerRewards()).thenReturn(Collections.singletonMap("C001", Map.of("January", 90)));

        mockMvc.perform(get("/rewards/customers/rewards"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.C001.January", is(90)));

        verify(rewardsService, times(1)).getAllCustomerRewards();
    }

    @Test
    void getCustomerRewards_ShouldReturnCustomerRewards() throws Exception {
        when(rewardsService.getCustomerRewards("C001")).thenReturn(Map.of("January", 90));

        mockMvc.perform(get("/rewards/customers/C001/rewards"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.January", is(90)));

        verify(rewardsService, times(1)).getCustomerRewards("C001");
    }

    @Test
    void addCustomer_ShouldReturnSuccessMessage() throws Exception {
        mockMvc.perform(post("/rewards/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": \"C001\", \"name\": \"John Doe\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Customer added successfully!"));

        verify(rewardsService, times(1)).addCustomer(any(Customer.class));
    }
}
