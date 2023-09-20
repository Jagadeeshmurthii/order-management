package com.kafka.kafka_project.service;

import com.kafka.kafka_project.dto.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public Order produceOrder(Order order) {
        kafkaTemplate.send("order", order);
        logger.info("Message has been produced to kafka topic" + order);
        return order;
    }

}
