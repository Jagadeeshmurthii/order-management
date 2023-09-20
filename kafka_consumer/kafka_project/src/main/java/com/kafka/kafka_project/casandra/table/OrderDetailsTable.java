package com.kafka.kafka_project.casandra.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsTable {
    @PrimaryKey
    private UUID orderDetailsId;
    private int itemId;
    private int orderId;
    private String itemName;
    private int itemQty;
    private int customerId;

}
