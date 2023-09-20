package com.endpoints.url.casandra.table;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.sql.Timestamp;

@Table
@Data
public class ItemTable {
    @PrimaryKey
    private int  itemId;
    private String itemName;
    private String itemDesc;
    private int itemCost;
    private Timestamp createTs;
    private Timestamp upDateTs;
}
