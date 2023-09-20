package com.endpoints.url.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class Order {

    private int orderId;
    private int orderCost;
    private Timestamp orderCreateTs;
    private Timestamp orderUpdateTs;
    private String orderStatus;
    private Customer customer;
    private List<Item> itemList;

}
