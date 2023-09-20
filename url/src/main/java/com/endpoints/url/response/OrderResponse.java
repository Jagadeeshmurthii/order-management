package com.endpoints.url.response;


import lombok.Data;

@Data
public class OrderResponse {

    private int orderId;
    private int cost;
    private int numberOfItems;
    private String status;
}
