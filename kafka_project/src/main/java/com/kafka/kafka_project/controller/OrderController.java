package com.kafka.kafka_project.controller;


import com.kafka.kafka_project.dto.Order;
import com.kafka.kafka_project.service.CustomerService;
import com.kafka.kafka_project.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produce")
public class OrderController {
    Logger logger = LoggerFactory.getLogger(CustomerService.class);
    @Autowired
  private   OrderService orderService;

    @PostMapping("/order")
    public Order produceOrder(@RequestBody Order order) {
        try {
            orderService.produceOrder(order);
            logger.info("Message sent to topic: " + order);
            return order;
        } catch (Exception e) {
            logger.error("Error occurred while processing order: " + e.getMessage(), e);
            throw new RuntimeException("Failed to process order request", e);
        }
    }
}
