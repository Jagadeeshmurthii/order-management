package com.kafka.kafka_project.casandra.table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.sql.Timestamp;

@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemTable {
    @PrimaryKey
    private int itemId;
    private String itemName;
    private String itemDesc;
    private int itemQty;
    private int itemCost;
    private Timestamp createTs;
    private Timestamp upDateTs;
}
