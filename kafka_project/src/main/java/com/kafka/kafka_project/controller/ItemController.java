package com.kafka.kafka_project.controller;

import com.kafka.kafka_project.dto.Item;
import com.kafka.kafka_project.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produce")
public class ItemController {

    Logger logger = LoggerFactory.getLogger(ItemController.class);
    @Autowired
    private ItemService itemService;


    @PostMapping("/item")
    public Item produceItem(@RequestBody Item item) {
        try {
            logger.info("produce message to the topic" + item);
            itemService.produceItem(item);
            return item;
        } catch (Exception e) {
            logger.error("Error occurred while processing item: " + e.getMessage(), e);
            throw new RuntimeException("Failed to process item request", e);
        }
    }
}
