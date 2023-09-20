package com.kafka.kafka_project.casandra.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.kafka_project.casandra.repo.MessageRepo;
import com.kafka.kafka_project.casandra.table.MessageTable;
import com.kafka.kafka_project.dto.Customer;
import com.kafka.kafka_project.dto.Item;
import com.kafka.kafka_project.dto.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.UUID;

@Service
public class MessageService {
    Logger logger = LoggerFactory.getLogger(MessageService.class);
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    MessageRepo messageRepo;

    private void saveMessageToDatabase(Object message, int entityId) {
        try {
            String jsonMessage = objectMapper.writeValueAsString(message);
            Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
            MessageTable entity = new MessageTable();
            entity.setMsgId(UUID.randomUUID());
            entity.setEntityId(entityId);
            entity.setMsgString(jsonMessage);
            entity.setMsgCreateTs(currentTimestamp);
            messageRepo.save(entity);
        } catch (JsonProcessingException e) {
            logger.error("data not saved in message table");
        }
    }

    @KafkaListener(topics = "customer", groupId = "group-3")
    public void consumeCustomerMessage(Customer message) {
        saveMessageToDatabase(message, message.getCustomerId());
    }

    @KafkaListener(topics = "item", groupId = "group-3")
    public void consumeItemMessage(Item message) {
        saveMessageToDatabase(message, message.getItemId());
    }

    @KafkaListener(topics = "order", groupId = "group-3")
    public void consumeOrderMessage(Order message) {
        saveMessageToDatabase(message, message.getOrderId());
    }
}


