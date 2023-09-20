package com.kafka.kafka_project.casandra.service;

import com.kafka.kafka_project.casandra.repo.ItemRepo;
import com.kafka.kafka_project.casandra.table.ItemTable;
import com.kafka.kafka_project.dto.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    Logger logger = LoggerFactory.getLogger(ItemService.class);

   @Autowired
    ItemRepo itemRepo;

    @KafkaListener(topics = "item", groupId = "group-3")
    public void consumeItem(Item item) {
        try {
            logger.info("Consuming message from Kafka topic: {}", item);
            ItemTable itemTable = itemRepo.save(convertDtoToTable(item));
            itemRepo.save(itemTable);
            logger.info("Saved item to the database: {}", itemTable);
        } catch (Exception e) {
            logger.error("Error while consuming or processing item: {}", e.getMessage(), e);
        }
    }
    public ItemTable convertDtoToTable(Item item) {
        ItemTable itemTable = new ItemTable();
        itemTable.setItemId(item.getItemId());
        itemTable.setItemName(item.getItemName());
        itemTable.setItemQty(item.getItemQty());
        itemTable.setItemCost(item.getItemCost());
        itemTable.setItemDesc(item.getItemDesc());
        itemTable.setCreateTs(item.getCreateTs());
        itemTable.setUpDateTs(item.getUpDateTs());
        return itemTable;
    }
}