package com.kafka.kafka_project.controller;

import com.kafka.kafka_project.dto.Customer;
import com.kafka.kafka_project.service.CustomerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/produce")
public class CustomerController {

    Logger logger = LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    private CustomerService customerService;

    @PostMapping("/customer")
    public Customer sendMessage(@RequestBody Customer customer) {
        try {
            logger.info("produce message to kafka topic" + customer);
            customerService.produceCustomer(customer);
            return customer;
        } catch (Exception e) {
            logger.error("Error occurred while processing customer: " + e.getMessage(), e);
            throw new RuntimeException("Failed to process customer request", e);
        }
    }
}
