package com.kafka.kafka_project.sql.service;

import com.kafka.kafka_project.dto.Customer;

import com.kafka.kafka_project.sql.model.CustomerEntity;
import com.kafka.kafka_project.sql.repo.CustomerRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    Logger logger = LoggerFactory.getLogger(CustomerEntity.class);

    @Autowired
    CustomerRepo customerRepo;

    @KafkaListener(topics = "customer", groupId = "group-2")
    public void consumeCustomer(Customer customer) {
        try {
            logger.info("Consuming message from Kafka topic: {}", customer);
            CustomerEntity customerEntity = convertDtoToEntity(customer);
            customerRepo.save(customerEntity);
            logger.info("Saved customer to the database: {}", customerEntity);
        } catch (Exception e) {
            logger.error("Error while consuming or processing customer: {}", e.getMessage(), e);

        }
    }
    public CustomerEntity convertDtoToEntity(Customer customer) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setCustomerId(customer.getCustomerId());
        customerEntity.setCustomerName(customer.getCustomerName());
        customerEntity.setCity(customer.getCity());
        customerEntity.setAddress(customer.getAddress());
        customerEntity.setPhone(customer.getPhone());
        customerEntity.setEmailId(customer.getEmailId());
        customerEntity.setCountry(customer.getCountry());
        return customerEntity;
    }
}
