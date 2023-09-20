package com.kafka.kafka_project.dto;

import lombok.*;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    public int itemId;
    public String itemName;
    public String itemDesc;
    public int itemQty;
    public int itemCost;
    public Timestamp createTs;
    public Timestamp upDateTs;
}
