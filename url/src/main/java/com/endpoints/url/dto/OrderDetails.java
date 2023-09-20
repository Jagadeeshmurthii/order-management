package com.endpoints.url.dto;


import lombok.Data;

@Data
public class OrderDetails {

    private int orderId;
    private int orderDetailsId;
    private int itemId;
    private String itemName;
    private int itemQty;
    private int customerId;
}
