package com.kafka.kafka_project.sql.service;

import com.kafka.kafka_project.casandra.repo.OrderDetailsRepo;

import com.kafka.kafka_project.casandra.table.OrderDetailsTable;
import com.kafka.kafka_project.dto.Item;
import com.kafka.kafka_project.dto.Order;
import com.kafka.kafka_project.sql.model.OrderEntity;
import com.kafka.kafka_project.sql.repo.CustomerRepo;
import com.kafka.kafka_project.sql.repo.OrderRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class OrderService {

    Logger logger= LoggerFactory.getLogger(OrderService.class);
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    OrderDetailsRepo orderDetailsRepo;


    @KafkaListener(topics = "order", groupId = "group-2")
    public Order consumeOrder(Order order) {
        try {
            logger.info("Consuming message from Kafka topic: {}", order);
            OrderEntity orderEntity = convertDtoToEntity(order);
            orderRepo.save(orderEntity);
            logger.info("Saved order to the database: {}", orderEntity);
        } catch (Exception e) {
            logger.error("Error while consuming or processing order: {}", e.getMessage(), e);
        }
        return order;
    }

    private OrderEntity convertDtoToEntity(Order order) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(order.getOrderId());
        orderEntity.setOrderCost(order.getOrderCost());
        orderEntity.setOrderStatus(order.getOrderStatus());
        orderEntity.setOrderCreateTs(order.getOrderCreateTs());
        orderEntity.setOrderUpdateTs(order.getOrderUpdateTs());
        orderEntity.setCustomerEntity(customerRepo.findById(order.getCustomer().getCustomerId()));
        logger.info("Fetching customer for order: {}", orderEntity);
        for (Item item : order.getItem()) {
            try {
                OrderDetailsTable orderDetailsTable = new OrderDetailsTable();
                orderDetailsTable.setOrderDetailsId(UUID.randomUUID());
                orderDetailsTable.setItemId(item.getItemId());
                orderDetailsTable.setItemName(item.getItemName());
                orderDetailsTable.setItemQty(item.getItemQty());
                orderDetailsTable.setOrderId(order.getOrderId());
                orderDetailsTable.setCustomerId(order.getCustomer().getCustomerId());
                orderDetailsRepo.save(orderDetailsTable);
            } catch (Exception e) {
                logger.error("Error while consuming or processing order: {}", e.getMessage(), e);
            }
        }
        return orderEntity;
    }
}

