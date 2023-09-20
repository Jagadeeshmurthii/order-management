package com.kafka.kafka_project.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class OrderDetails {
    private int orderId;
    private UUID orderDetailsId;
    private int itemId;
    private String itemName;
    private int itemQty;
    private int customerId;
}
