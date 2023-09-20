package com.kafka.kafka_project.casandra.service;

import com.kafka.kafka_project.casandra.table.OrderDetailsTable;
import com.kafka.kafka_project.dto.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailsService {
    Logger logger = LoggerFactory.getLogger(OrderDetailsService.class);

    @KafkaListener(topics = "order", groupId = "group-3")
    public OrderDetailsTable consumeOrderDetails(Order orderDetails) {
        logger.info("consume message from kafka topic");
        return null;
    }
}
