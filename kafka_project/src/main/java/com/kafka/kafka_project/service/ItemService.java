package com.kafka.kafka_project.service;


import com.kafka.kafka_project.dto.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
@Service
public class ItemService {

    Logger logger = LoggerFactory.getLogger(ItemService.class);
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public Item produceItem(Item item) {
        kafkaTemplate.send("item", item);
        logger.info("Message has been produced to kafka topic" + item);
        return item;
    }

}
