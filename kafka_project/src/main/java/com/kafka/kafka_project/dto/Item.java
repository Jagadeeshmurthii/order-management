package com.kafka.kafka_project.dto;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class Item {
    private int itemId;
    private String itemName;
    private String itemDesc;
    private int itemQty;
    private int itemCost;
    private Timestamp createTs;
    private Timestamp upDateTs;

}
