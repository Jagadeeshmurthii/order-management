package com.kafka.kafka_project.service;

import com.kafka.kafka_project.dto.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
@Service
public class CustomerService {

    Logger logger = LoggerFactory.getLogger(CustomerService.class);
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public Customer produceCustomer(Customer customer) {
        kafkaTemplate.send("customer", customer);
        logger.info("Message has been produced to kafka topic" + customer);
        return customer;
    }

}
