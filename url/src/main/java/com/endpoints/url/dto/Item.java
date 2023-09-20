package com.endpoints.url.dto;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class Item {

    private int itemId;
    private String itemName;
    private int itemCost;
    private Timestamp createTs;
    private Timestamp upDateTs;
    private String itemDesc;
}
